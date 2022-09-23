package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {

    Integer insert(@Param("post") Post post);

    List<Post> selectAll();

}
