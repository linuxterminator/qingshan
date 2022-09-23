package com.hu.qingshan.modules.Comment.controller;

import com.hu.qingshan.model.RequestParam.CommentParam;
import com.hu.qingshan.modules.Comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public Object queryPostComments(@RequestParam("postId") String postId){
        return commentService.QueryCommentUnderPost(postId);
    }

    @PostMapping("/comments")
    public String addPostComment(@RequestBody CommentParam commentParam){
        return commentService.saveComment(commentParam);
    }

}
