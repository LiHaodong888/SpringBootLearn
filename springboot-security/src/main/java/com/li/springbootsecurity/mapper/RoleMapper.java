package com.li.springbootsecurity.mapper;

import com.li.springbootsecurity.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role findRoleByUserId(@Param("userId") Integer userId);
}

