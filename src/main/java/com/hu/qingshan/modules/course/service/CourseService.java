package com.hu.qingshan.modules.course.service;

import com.hu.qingshan.model.DatabaseModel.Course;
import com.hu.qingshan.model.RequestParam.ChapterParam;
import com.hu.qingshan.model.RequestParam.CourseParam;
import com.hu.qingshan.model.RequestParam.LessonParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    String addNewCourse(CourseParam courseParam);

    String addNewChapter(ChapterParam chapterParam,String courseId);

    String addNewLesson(LessonParam lessonParam,String courseId,String chapterId);

    List<Course> listAllCourse();
}
