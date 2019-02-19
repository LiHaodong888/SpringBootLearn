package com.li.springbootmybatis.dao;

import com.li.springbootmybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author lihaodong
 * @Date 2019/2/19 09:18
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user(name) values(#{name})")
    int add(@Param("name") String name);

    @Update("update user set name = #{name} where id = #{id}")
    int update(@Param("name") String name, @Param("id") int id);

    @Delete("delete from user where id = #{id}")
    int delete(int id);

    @Select("select id, name from user where id = #{id}")
    User findUser(@Param("id") int id);

    @Select("select id, name from user")
    List<User> findUserList();


}
