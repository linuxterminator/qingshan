package com.hu.qingshan.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hu.qingshan.repository")
public class MybatisConfig {
}
