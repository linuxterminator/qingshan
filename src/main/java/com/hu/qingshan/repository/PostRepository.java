package com.hu.qingshan.repository;

import com.hu.qingshan.model.DatabaseModel.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostRepository {

    Integer insert(@Param("post") Post post);

    List<Post> selectAll();

}
