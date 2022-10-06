package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Lesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LessonMapper {

    void saveLesson(@Param("lesson")Lesson lesson);

    List<Lesson> queryByCourseId(@Param("courseId") String courseId);

    List<Lesson> queryByCourseIdAndChapterId(@Param("courseId") String courseId,@Param("chapterId") String chapterId);
}
