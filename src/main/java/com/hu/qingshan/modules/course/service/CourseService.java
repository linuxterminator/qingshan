package com.hu.qingshan.modules.course.service;

import com.hu.qingshan.model.DTO.BaseChapterDTO;
import com.hu.qingshan.model.DTO.CourseDetialDTO;
import com.hu.qingshan.model.DTO.SimpleCourseDTO;
import com.hu.qingshan.model.RequestParam.ChapterParam;
import com.hu.qingshan.model.RequestParam.CourseParam;
import com.hu.qingshan.model.RequestParam.LessonParam;

import java.util.List;

public interface CourseService {

    String addNewCourse(CourseParam courseParam);

    String addNewChapter(ChapterParam chapterParam,String courseId);

    String addNewLesson(LessonParam lessonParam,String courseId,String chapterId);

    List<SimpleCourseDTO> querySimpleCourse();

    List<CourseDetialDTO> queryDetialCourse();

    List<BaseChapterDTO> queryBaseChapter(String courseId);

    String removeCourse(String courseId);
}
