package com.example.boot.blog.handler;

import com.example.boot.blog.exception.IdTypeException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-10 19:44
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handlerBindException(BindException bindException, Model model){
        BindingResult bindingResult = bindException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        model.addAttribute("errors", fieldErrors);

        return "/blog/error/bind";
    }

    @ExceptionHandler
    public String handlerIdTypeException(IdTypeException idTypeException, Model model){
        String message = idTypeException.getMessage();
        model.addAttribute("message", message);

        return "/blog/error/error";
    }

    @ExceptionHandler
    public String handlerDefaultException(Exception e, Model model){
        model.addAttribute("message", "请稍后重试!");

        return "/blog/error/error";
    }
}
