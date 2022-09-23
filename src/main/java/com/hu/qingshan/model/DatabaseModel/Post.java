package com.hu.qingshan.model.DatabaseModel;

import com.hu.qingshan.model.DatabaseModel.Base.BaseModel;
import lombok.Data;

/**
 * 1.nanoid
 * 2.文章标题
 * 3.文章内容
 * 4.文章作者
 * 5.文章创建时间
 * 6.文章收藏数
 * 8.文章浏览量
 * 9.文章标签集合
 */
@Data
public class Post extends BaseModel {

    private String postId;
    private String title;
    private String content;
    private Integer collect;
    private Integer view;
    private String author;
    private String userId;

    public Post initAttribute(){
        this.postId = GetNanoid();
        this.collect = 0;
        this.view = 1;
        return this;
    }

}

