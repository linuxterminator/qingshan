package com.hu.qingshan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        List<String> urlList = Arrays.asList("/accountlogin", "/accountlogout", "/wxlogin", "/accountsignup", "/error", "/refreshtoken");
//
//        registry
//                .addInterceptor(new AppInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(urlList);
//    }

}
