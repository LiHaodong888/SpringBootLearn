package com.li.springbootsecurity.exception;

import com.li.springbootsecurity.bo.ResultJson;
import lombok.Getter;

/**
 * @Author 李号东
 * @Description 自定义异常
 * @Date 00:38 2019-03-17
 * @Param 
 * @return 
 **/
@Getter
public class CustomException extends RuntimeException{
    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}
