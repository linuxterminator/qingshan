package com.hu.qingshan.core.Untils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hu.qingshan.core.ConfigProperties.OssProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * oss工具
 */
@Component
public class OssUntil {

    private final OssProperties ossProperties;
    private final OSS ossClient;

    public OssUntil(OssProperties ossProperties){
        this.ossClient = new OSSClientBuilder()
                        .build(ossProperties.getEndPoint(),ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        this.ossProperties = ossProperties;
    }

    /**
     * 头像上传
     */
    public String iconUpLoad(MultipartFile iconFile){
        String iconName = generatorOssFileName(iconFile.getOriginalFilename());
        try{
            this.ossClient.putObject(ossProperties.getBucketName(),iconName,iconFile.getInputStream());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        return getSuccessComponents(iconName).toString();
    }

    /**
     * 视频上传
     */
    public List<String> videoUpload(List<MultipartFile> videoFile){

        List<String> successUrl = new ArrayList<>();

        videoFile.forEach(file->{

            try {
                String fileName = generatorOssFileName(file.getOriginalFilename());
                this.ossClient.putObject(ossProperties.getBucketName(),fileName,file.getInputStream());
                successUrl.add(getSuccessComponents(fileName).toString());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ossClient.shutdown();
        return successUrl;

    }

    /**
     * 视频删除
     */
    public void videoDelete(){

    }

    /**
     * uri拼接
     * @param fileName
     * @return
     */
    private UriComponents getSuccessComponents(String fileName){
        StringBuffer hostName = new StringBuffer();
        hostName.append(ossProperties.getBucketName())
                .append(".")
                .append(ossProperties.getEndPoint());

        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostName.toString())
                .path(fileName)
                .encode(StandardCharsets.UTF_8)
                .build();
    }

    /**
     * 获取生成的文件名，使用时间来拼接，oss的文件名需要包含对应的目录
     * @param fileName
     * @return
     */
    private String generatorOssFileName(String fileName){
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer
                .append(ossProperties.getDir())
                .append(LocalDateTime.now())
                .append("_")
                .append(fileName)
                .toString();
    }

}
