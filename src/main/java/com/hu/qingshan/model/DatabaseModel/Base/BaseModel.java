package com.hu.qingshan.model.DatabaseModel.Base;

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
    protected LocalDateTime updateDate;
    protected String id;

    public void initData(){
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.id = NanoIdUtils.randomNanoId();
    }

    public static String GetNanoid(){
        return NanoIdUtils.randomNanoId();
    }

}
