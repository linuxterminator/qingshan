package com.hu.qingshan.core.config;

import com.hu.qingshan.core.interceptor.AppInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> urlList = Arrays.asList("/accountlogin", "/accountlogout", "/wxlogin", "/accountsignup", "/error", "/refreshtoken","/upload","/course");

        registry
                .addInterceptor(new AppInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(urlList);
    }

}
