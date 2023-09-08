package com.sgg.springboot3.boot3.handler;

import com.sgg.springboot3.boot3.exception.BookNotFoundException;
import com.sgg.springboot3.boot3.exception.IsbnNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 23:04
 * @Version 1.0
 */
@RestControllerAdvice   // 如果自定义的RFC7807 Exception, 需要注释掉，并且去properties文件里添加7807支持
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class})
    public ProblemDetail bookNotFoundExceptionHandler(BookNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, e.getMessage());

        problemDetail.setType(URI.create("/error/not-found"));
        problemDetail.setTitle("无此图书");

        //增加自定义的字段
        problemDetail.setProperty("时间", Instant.now());
        problemDetail.setProperty("Email", "xxxxx-services@126.com");

        return problemDetail;
    }



}
