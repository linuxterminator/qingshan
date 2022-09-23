package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDTO {

    private String commentId;
    private String content;
    private LocalDateTime createDate;
    private String postId;
    private String fatherId;
    private String acceptId;
    private String replayId;
    private String good;

    // 发送者对象
    private UserDTO replayer;
    // 接受者对象
    private UserDTO accepter;
    // 子评论
    private List<CommentDTO> subComment;
}
