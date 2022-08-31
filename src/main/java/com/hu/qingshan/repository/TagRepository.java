package com.hu.qingshan.repository;

import com.hu.qingshan.model.DatabaseModel.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagRepository {

    Integer insert(@Param("tag") Tag tag);

    List<Tag> selectAll();

    Integer isTagExists(@Param("tagName") String tagName);

    Tag selectByName(@Param("tagName") String tagName);

    Tag selectById(@Param("tagId") String tagId);

}
