package com.hu.qingshan.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostWithTagMapper {

    Integer insert(@Param("postId") String postId, @Param("tagId") String tagId);

    List<String> QueryTagIdByPostId(@Param("postId") String postId);

}
