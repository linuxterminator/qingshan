package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Lesson;
import org.apache.ibatis.annotations.Param;

public interface LessonMapper {

    void saveLesson(@Param("lesson")Lesson lesson);
}
