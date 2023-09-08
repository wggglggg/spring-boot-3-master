package com.sgg.springboot3.boot3.exception;

/**
 * ClassName: BookNotFoundException
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 22:48
 * @Version 1.0
 */
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
