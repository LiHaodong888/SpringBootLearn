package com.li.springbootsecurity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户类
 * @author lihaodong
 * @since 2019-03-14
 */
@Setter
@Getter
@ToString
@TableName("user")
public class User extends Model<User>{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

}
