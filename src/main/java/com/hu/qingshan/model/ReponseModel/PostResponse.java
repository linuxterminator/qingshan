package com.hu.qingshan.model.ReponseModel;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {

    private String postId;
    private String title;
    private String content;
    private Integer collect;
    private Integer view;
    private String author;
    private LocalDateTime createDate;
    private List<Tag> tagList;

    @Data
    public static class Tag{
        private String tagName;
    }

}
