package com.hu.qingshan.model.DatabaseModel;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 提取公共方法
 * 1.获取nanoid
 * 2.创建时间
 */
@Data
public abstract class BaseModel {

    protected LocalDateTime createDate;

    protected BaseModel(){
        createDate = LocalDateTime.now();
    }

    protected static String GetNanoid(){
        return NanoIdUtils.randomNanoId();
    }

}
