package com.li.springbootsecurity.bo;

import lombok.Getter;

/**
 * @Author 李号东
 * @Description 状态码
 * @Date 00:41 2019-03-17
 * @Param
 * @return
 **/
@Getter
public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "参数或者语法不对"),
    UNAUTHORIZED(401, "认证失败"),
    LOGIN_ERROR(401, "登陆失败，用户名或密码错误"),
    NO_TOKEN(401, "认证失败，缺少头部token值"),
    TOKEN_INVALID(401, "认证失败，token值认证失败"),
    FORBIDDEN(403, "权限不足,禁止访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    OPERATE_ERROR(405, "操作失败，请求操作的资源不存在"),
    TIME_OUT(408, "请求超时"),
    SERVER_ERROR(500, "服务器内部错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
