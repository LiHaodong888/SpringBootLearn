package com.li.springbootsecurity.utils;

import com.alibaba.fastjson.JSON;
import com.li.springbootsecurity.model.Role;
import com.li.springbootsecurity.security.SecurityUser;
import io.jsonwebtoken.CompressionCodecs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Classname JwtTokenUtil
 * @Description JWT工具类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-14 14:54
 * @Version 1.0
 */
@Component
public class JwtTokenUtil {

    private static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";

    private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);

    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 有效期
     */
    @Value("${jwt.expiration}")
    private Long accessTokenExpiration;

    /**
     * 刷新有效期
     */
    @Value("${jwt.expiration}")
    private Long refreshTokenExpiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;


    /**
     * 根据token 获取用户信息
     * @param token
     * @return
     */
    public SecurityUser getUserFromToken(String token) {
        SecurityUser userDetail;
        try {
            final Claims claims = getClaimsFromToken(token);
            int userId = getUserIdFromToken(token);
            String username = claims.getSubject();
            String roleName = claims.get(CLAIM_KEY_AUTHORITIES).toString();
            Role role = Role.builder().name(roleName).build();
            userDetail = new SecurityUser(userId, username, role, "");
        } catch (Exception e) {
            userDetail = null;
        }
        return userDetail;
    }

    /**
     * 根据token 获取用户ID
     * @param token
     * @return
     */
    private int getUserIdFromToken(String token) {
        int userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = Integer.parseInt(String.valueOf(claims.get(CLAIM_KEY_USER_ID)));
        } catch (Exception e) {
            userId = 0;
        }
        return userId;
    }


    /**
     * 根据token 获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token 获取生成时间
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }


    /**
     * 生成令牌
     *
     * @param userDetail 用户
     * @return 令牌
     */
    public String generateAccessToken(SecurityUser userDetail) {
        Map<String, Object> claims = generateClaims(userDetail);
        claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(userDetail.getAuthorities()).get(0));
        return generateAccessToken(userDetail.getUsername(), claims);
    }

    /**
     * 根据token 获取过期时间
     * @param token
     * @return
     */
    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


    /**
     * 验证token 是否合法
     * @param token  token
     * @param userDetails  用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        SecurityUser userDetail = (SecurityUser) userDetails;
        final long userId = getUserIdFromToken(token);
        final String username = getUsernameFromToken(token);
        return (userId == userDetail.getId()
                && username.equals(userDetail.getUsername())
                && !isTokenExpired(token)
        );
    }

    /**
     * 根据用户信息 重新获取token
     * @param userDetail
     * @return
     */
    public String generateRefreshToken(SecurityUser userDetail) {
        Map<String, Object> claims = generateClaims(userDetail);
        // 只授于更新 token 的权限
        String[] roles = new String[]{JwtTokenUtil.ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(roles));
        return generateRefreshToken(userDetail.getUsername(), claims);
    }

    public void putToken(String userName, String token) {
        tokenMap.put(userName, token);
    }

    public void deleteToken(String userName) {
        tokenMap.remove(userName);
    }

    public boolean containToken(String userName, String token) {
        return userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token);
    }

    /***
     * 解析token 信息
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成失效时间
     * @param expiration
     * @return
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 生成时间是否在最后修改时间之前
     * @param created   生成时间
     * @param lastPasswordReset  最后修改密码时间
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


    private Map<String, Object> generateClaims(SecurityUser userDetail) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USER_ID, userDetail.getId());
        return claims;
    }

    /**
     * 生成token
     * @param subject  用户名
     * @param claims
     * @return
     */
    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, accessTokenExpiration);
    }

    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }


    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, refreshTokenExpiration);
    }


    /**
     * 生成token
     * @param subject  用户名
     * @param claims
     * @param expiration 过期时间
     * @return
     */
    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

}
