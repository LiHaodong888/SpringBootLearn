package com.li.springbootsecurity.exception;

import com.li.springbootsecurity.bo.ResultCode;
import com.li.springbootsecurity.bo.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Author 李号东
 * @Description 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 * @Date 00:38 2019-03-17
 * @Param
 * @return
 **/
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultJson handleCustomException(CustomException e){
        log.error(e.getResultJson().getMsg());
        return e.getResultJson();
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        return ResultJson.failure(ResultCode.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
