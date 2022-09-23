package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    List<Comment> findCommentsByPostId(@Param("postId") String postId);

    List<Comment> findAllSubCommentByFatherId(@Param("fatherId") String fatherId);
    void insertComment(@Param("comment") Comment comment);

}
