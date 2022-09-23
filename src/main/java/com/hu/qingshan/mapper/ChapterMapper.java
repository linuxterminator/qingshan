package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {

    List<Chapter> queryChaptersByCourseId(@Param("courseId") String courseId);

    void saveChapter(@Param("chapter")Chapter chapter);

}
