package com.li.springbootsecurity.bo;

import com.li.springbootsecurity.security.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 李号东
 * @Description //TODO 
 * @Date 00:42 2019-03-17
 * @Param 
 * @return 
 **/
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private SecurityUser userDetail;
}
