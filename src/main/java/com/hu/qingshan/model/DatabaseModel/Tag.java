package com.hu.qingshan.model.DatabaseModel;

import lombok.Data;

/**
 * 1.标签id
 * 2.标签名
 * 3.标签类型（用户创建的标签和系统标签）
 */
@Data
public class Tag extends BaseModel{

    private String tagId;
    private String tagName;
    private String tagType;

    public Tag initAttribute(){
        this.tagId = GetNanoid();
        return this;
    }

}
