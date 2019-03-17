package com.li.springbootsecurity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 角色类
 * @author lihaodong
 * @since 2019-03-14
 */
@Setter
@Getter
@Builder
@TableName("role")
public class Role extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;


}
