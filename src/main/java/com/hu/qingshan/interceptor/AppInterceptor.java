package com.hu.qingshan.interceptor;


import com.hu.qingshan.core.ExceptionAndEnums.AccountExceptionEnums;
import com.hu.qingshan.core.Untils.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * mvc里面配置需要拦截的路径，这里是处理逻辑，对需要拦截的路径进行处理，放行的路径不处理
 */
public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        可能需要进行""这个判断
        String token = Optional
                .ofNullable(request.getHeader("token"))
                .filter(item->!item.isBlank())
                .orElseThrow(()->AccountExceptionEnums.PLEASE_LOGIN.getException());

        Token.parseJWT(token);

        return true;
    }
}

