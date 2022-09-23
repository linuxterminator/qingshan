package com.hu.qingshan.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hu.qingshan.mapper")
public class MybatisConfig {
}
