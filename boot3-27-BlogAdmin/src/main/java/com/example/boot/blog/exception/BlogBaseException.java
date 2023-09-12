package com.example.boot.blog.exception;

/**
 * ClassName: BlogBaseException
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-11 17:34
 * @Version 1.0
 */
public class BlogBaseException extends RuntimeException{
    public BlogBaseException() {
        super();
    }

    public BlogBaseException(String message) {
        super(message);
    }
}
