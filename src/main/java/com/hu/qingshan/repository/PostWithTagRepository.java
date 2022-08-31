package com.hu.qingshan.repository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostWithTagRepository {

    Integer insert(@Param("postId") String postId, @Param("tagId") String tagId);

    List<String> QueryTagIdByPostId(@Param("postId") String postId);

}
