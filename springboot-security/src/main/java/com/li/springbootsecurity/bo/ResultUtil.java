package com.li.springbootsecurity.bo;

import com.alibaba.fastjson.JSON;
import com.li.springbootsecurity.bo.ResultCode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname ResultUtil
 * @Description TODO
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-17 08:33
 * @Version 1.0
 */
public class ResultUtil {

    public static void writeJavaScript(HttpServletResponse response, ResultCode resultCode, String msg) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 状态
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(ResultJson.failure(resultCode,msg)));
        printWriter.flush();
    }

}
