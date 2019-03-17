package com.li.springbootsecurity.config;

import com.li.springbootsecurity.security.SecurityUser;
import com.li.springbootsecurity.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author 李号东
 * @Description token过滤器来验证token有效性 引用的stackoverflow一个答案里的处理方式
 * @Date 00:32 2019-03-17
 * @Param
 * @return
 **/
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String authTokenStart;

    @Resource
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authToken = request.getHeader(this.tokenHeader);
        System.out.println(authToken);
        if (StringUtils.isNotEmpty(authToken) && authToken.startsWith(authTokenStart)) {
            authToken = authToken.substring(authTokenStart.length());
            log.info("请求" + request.getRequestURI() + "携带的token值：" + authToken);
            //如果在token过期之前触发接口,我们更新token过期时间，token值不变只更新过期时间
            //获取token生成时间
            Date createTokenDate = jwtTokenUtil.getCreatedDateFromToken(authToken);
            log.info("createTokenDate: " + createTokenDate);

        } else {
            // 不按规范,不允许通过验证
            authToken = null;
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        log.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication " + username);

        if (jwtTokenUtil.containToken(username, authToken) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityUser userDetail = jwtTokenUtil.getUserFromToken(authToken);
            if (jwtTokenUtil.validateToken(authToken, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info(String.format("Authenticated userDetail %s, setting security context", username));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
