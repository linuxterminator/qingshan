package com.hu.qingshan.core.GlobalHandle;

import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandle {

    // requestbody验证失败异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object RequestBodyValidError(MethodArgumentNotValidException methodArgumentNotValidException){
        return methodArgumentNotValidException.getAllErrors();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseBody
    public String JwtExceptionHandle(JwtException jwtException){
        return jwtException.getMessage();
    }

}
