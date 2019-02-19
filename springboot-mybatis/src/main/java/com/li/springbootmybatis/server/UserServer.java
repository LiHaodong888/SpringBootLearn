package com.li.springbootmybatis.server;

import com.li.springbootmybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserServer {

    int add(@Param("name") String name);

    int update(@Param("name") String name, @Param("id") int id);

    int delete(int id);

    User findUser(@Param("id") int id);

    List<User> findUserList();
}
