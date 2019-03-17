package com.li.springbootsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.springbootsecurity.exception.CustomException;
import com.li.springbootsecurity.bo.ResponseUserToken;
import com.li.springbootsecurity.bo.ResultCode;
import com.li.springbootsecurity.bo.ResultJson;
import com.li.springbootsecurity.model.User;
import com.li.springbootsecurity.mapper.UserMapper;
import com.li.springbootsecurity.security.SecurityUser;
import com.li.springbootsecurity.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.springbootsecurity.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 * @author lihaodong
 * @since 2019-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public User findByUserName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询条件
        queryWrapper.lambda().eq(User::getUsername, name);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public ResponseUserToken login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        //存储token
        jwtTokenUtil.putToken(username, token);
        return new ResponseUserToken(token, userDetail);

    }

    @Override
    public SecurityUser getUserByToken(String token) {
        token = token.substring(tokenHead.length());
        return jwtTokenUtil.getUserFromToken(token);
    }

    private Authentication authenticate(String username, String password) {
        try {
            // 该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，
            // 如果正确，则存储该用户名密码到security 的 context中
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }
}
