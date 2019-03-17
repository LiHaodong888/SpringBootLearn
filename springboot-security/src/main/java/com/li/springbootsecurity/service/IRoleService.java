package com.li.springbootsecurity.service;

import com.li.springbootsecurity.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") Integer userId);

}
