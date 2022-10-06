package com.hu.qingshan.config;

import com.hu.qingshan.interceptor.MybatisInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hu.qingshan.mapper")
public class MybatisConfig {

    @Bean
    public MybatisInterceptor mybatisInterceptor(){
        MybatisInterceptor mybatisInterceptor = new MybatisInterceptor();
        return mybatisInterceptor;
    }

}
