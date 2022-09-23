package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

    Integer insert(@Param("tag") Tag tag);

    List<Tag> selectAll();

    Tag isTagNameExists(@Param("tagName") String tagName);

    Tag selectByName(@Param("tagName") String tagName);

    Tag selectById(@Param("tagId") String tagId);

}
