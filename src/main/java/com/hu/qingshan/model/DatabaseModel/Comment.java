package com.hu.qingshan.model.DatabaseModel;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {

//    评论id
    private String commentId;
    private String content;
    private LocalDateTime createDate;
//    文章id
    private String postId;
//    父id
    private String fatherId;
//    回复者id
    private String replayId;
//    被回复id
    private String acceptId;
//    点赞数
    private Integer good;

    public Comment initAttribute(){
        this.commentId = NanoIdUtils.randomNanoId();
        this.createDate = LocalDateTime.now();
        this.good = 0;
        return this;
    }

}
