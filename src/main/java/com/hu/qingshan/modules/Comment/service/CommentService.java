package com.hu.qingshan.modules.Comment.service;

import com.hu.qingshan.model.RequestParam.CommentParam;

public interface CommentService {
    Object QueryCommentUnderPost(String postId);

    String saveComment(CommentParam commentParam);

}