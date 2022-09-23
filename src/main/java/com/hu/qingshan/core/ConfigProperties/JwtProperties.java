package com.hu.qingshan.core.ConfigProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "authenticate")
@Data
@Configuration
public class JwtProperties {

    private String secret;
    private String expire;

}
