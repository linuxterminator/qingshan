package com.hu.qingshan.repository;

import com.hu.qingshan.mapper.ChapterMapper;
import com.hu.qingshan.mapper.CourseMapper;
import com.hu.qingshan.mapper.LessonMapper;
import com.hu.qingshan.model.DatabaseModel.Chapter;
import com.hu.qingshan.model.DatabaseModel.Course;
import com.hu.qingshan.model.DatabaseModel.Lesson;
import org.springframework.stereotype.Component;

@Component
public class CourseRepository {

    private final CourseMapper courseMapper;
    private final ChapterMapper chapterMapper;
    private final LessonMapper lessonMapper;

    public CourseRepository(CourseMapper courseMapper, ChapterMapper chapterMapper, LessonMapper lessonMapper) {
        this.courseMapper = courseMapper;
        this.chapterMapper = chapterMapper;
        this.lessonMapper = lessonMapper;
    }

    public void saveCourse(Course course){
        course.initSelfAttribute();
        courseMapper.saveCourse(course);
    }

    public void saveChapter(Chapter chapter){
        chapter.initSelfAttribute();
        chapterMapper.saveChapter(chapter);
    }

    public void saveLesson(Lesson lesson){
        lesson.initSelfAttribute();
        lessonMapper.saveLesson(lesson);
    }

    /**
     * 需要计算课程下的总课程数
     */
    public void listCourseById(){
//        List<Course> courses = courseMapper.queryChapters();
//
//        courses.stream().forEach(item->{});
//        List<Chapter> chapters = chapterMapper.queryChaptersByCourseId();
    }

}
