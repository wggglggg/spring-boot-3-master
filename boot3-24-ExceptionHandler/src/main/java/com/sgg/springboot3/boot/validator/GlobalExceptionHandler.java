package com.sgg.springboot3.boot.validator;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: GlobalEceptionHandler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 17:12
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler({ArithmeticException.class})
//    public String handlerArithmeticException(ArithmeticException e, Model model){
//        String message = e.getMessage();
//
//        model.addAttribute("error_info", message);
//        return "exp";
//    }

    // 一个类型的错误，无法同时两个方法来处理，所以
    @ExceptionHandler({ArithmeticException.class})
    @ResponseBody
    public Map<String, Object> handlerArithmeticExceptionJason(ArithmeticException e){
        String errorMessage = e.getMessage();
        Map<String ,Object> map = new HashMap<>();
        map.put("错误原因：", errorMessage);

        return map;
    }

    //处理JSR303 验证参数的异常
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Map<String, Object> handlerJSR303Exception(BindException e){
        System.out.println("=========JSR303==========");
        BindingResult bindingResult = e.getBindingResult();
        Map<String, Object> map = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (int i = 0; i < fieldErrors.size(); i++) {
                FieldError fieldError = fieldErrors.get(i);
                map.put(i + "-" + fieldError.getField()+"错误原因: ", fieldError.getDefaultMessage());
            }
        }

        return map;
    }

}
