package com.hu.qingshan.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private String postId;
    private String title;
    private String userId;
    private String content;
    private Integer collect;
    private Integer view;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<TagDTO> tagList;

}
