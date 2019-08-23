package com.xd.mybatisplusmultitenancy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Classname User
 * @Description 用户实体类
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-08-09 22:49
 * @Version 1.0
 */
@Data
@ToString
@Accessors(chain = true)
public class User {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 多租户Id
     */
    private Long tenantId;
}
