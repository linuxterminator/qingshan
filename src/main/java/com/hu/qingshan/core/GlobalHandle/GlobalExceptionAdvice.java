package com.hu.qingshan.core.GlobalHandle;

import com.hu.qingshan.core.ExceptionAndEnums.ApplicationException;
import com.hu.qingshan.core.Result.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参数验证异常处理
 * advice可以处理到达controller的异常，无法处理没有到达controller的异常
 */

/**
 * 处理来自拦截器token的异常
 * 多个controllerAdvice的顺序是什么，对异常进行处理，同一返回需要判断是否已经是Result对象了，如果已经是就不需要封装
 * controlleradvice被全局返回处理，还需要加上responseBody?
 * 由于用来解密的密钥是静态生成的，导致springboot重新启动后密钥发生变化，无法解析之前的token，但是refreshtoken依然存在
 * 导致一直提示已经登陆，密钥可以存在配置文件中
 * jjwt默认的方法提供了一个随机密钥，jwt会最终用这些加密成base64，我们需要手动指定密钥
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    // requestbody验证失败异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object RequestBodyValidError(MethodArgumentNotValidException methodArgumentNotValidException){
        return methodArgumentNotValidException.getAllErrors();
    }

    /**
     * 由拦截器拦截并抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public Result<String> jwtExpired(ExpiredJwtException e){
        return Result.fail(5001,"token已过期");
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public Result<String> jwtSignatureException(SignatureException e){
        return Result.fail(5000,"token无效");
    }

    /**
     * 通用业务异常
     */
    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public Result BusinessException(ApplicationException e){
        return Result.fail(e.getCode(),e.getMessage());
    }

}
