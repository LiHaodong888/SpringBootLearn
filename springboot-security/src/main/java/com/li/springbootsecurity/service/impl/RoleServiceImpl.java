package com.li.springbootsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.springbootsecurity.model.Role;
import com.li.springbootsecurity.mapper.RoleMapper;
import com.li.springbootsecurity.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色服务实现类
 * @author lihaodong
 * @since 2019-03-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role findRoleByUserId(Integer userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
