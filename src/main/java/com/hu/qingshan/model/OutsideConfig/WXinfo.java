package com.hu.qingshan.model.OutsideConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WXinfo {

    private String appid;
    private String secret;
    private String url;

}
