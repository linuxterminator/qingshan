package com.hu.qingshan.model.RequestParam;

import lombok.Data;

@Data
public class CommentParam {

    private String content;
    private String postId;
    private String fatherId;
    private String replayId;
    private String acceptId;

}
