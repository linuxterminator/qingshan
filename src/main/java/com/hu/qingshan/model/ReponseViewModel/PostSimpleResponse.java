package com.hu.qingshan.model.ReponseViewModel;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostSimpleResponse {

    private String postId;
    private String title;
    private String content;
    private Integer collect;
    private Integer view;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
