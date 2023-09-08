package com.sgg.springboot3.boot3.exception;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.LocalDateTime;


import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;


/**
 * ClassName: IsbnNotFoundException
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 22:41
 * @Version 1.0
 */

// //自定义异常类，让SpringMVC的异常处理器使用
public class IsbnNotFoundException extends ErrorResponseException {

    public IsbnNotFoundException(HttpStatusCode status, String detail) {
        super(status, createProblemDetail(status, detail), null);

    }

//    {
//        "type": "/error/isbn/info",
//            "title": "没有此图书",
//            "status": 404,
//            "detail": "ISBN: 没有203此图书",
//            "instance": "/book/isbn"
//    }

    public static ProblemDetail createProblemDetail(HttpStatusCode status, String detail){
        //封装RFC7807 字段
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle("没有此图书");
        problemDetail.setType(URI.create("/error/isbn/info"));

        // 再添加更多Exception信息
        problemDetail.setProperty("email", "abc@163.com");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
