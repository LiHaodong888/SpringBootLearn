package com.li.springbootmybatistx.dao;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;

/**
 * @ClassName UserMapper
 * @Author lihaodong
 * @Date 2019/2/19 09:18
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
public interface UserMapper {

    boolean updateUser(@Param("money") BigDecimal money, @Param("id") int  id);

}
