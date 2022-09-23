package com.hu.qingshan.core.ConfigProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "oss")
@Configuration
@Data
public class OssProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String dir;
    private String endPoint;

}
