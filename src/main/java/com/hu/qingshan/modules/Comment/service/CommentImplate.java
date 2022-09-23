package com.hu.qingshan.modules.Comment.service;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.Comment;
import com.hu.qingshan.model.RequestParam.CommentParam;
import com.hu.qingshan.repository.AppRepository;
import com.hu.qingshan.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentImplate extends ModelConvert implements CommentService{

    private final AppRepository appRepository;

    public CommentImplate(CommentRepository commentRepository){
        this.appRepository = commentRepository;
    }

    @Override
    public Object QueryCommentUnderPost(String postId){
        return ((CommentRepository)appRepository).queryCommentByRepository(postId);
    }

    @Override
    public String saveComment(CommentParam commentParam){

        appRepository.saveByRepository(ConvertToTarget(commentParam, Comment.class));

        return "评论成功";

    }
}

