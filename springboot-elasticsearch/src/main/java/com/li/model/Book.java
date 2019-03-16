package com.li.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Book
 * @Author lihaodong
 * @Date 2018/12/20 09:29
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {



    String id;
    String name;
    String message;
    Double price;
    Date creatDate;
}
