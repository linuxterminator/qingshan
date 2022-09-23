package com.hu.qingshan.modules.course.controller;

import com.hu.qingshan.model.RequestParam.ChapterParam;
import com.hu.qingshan.model.RequestParam.CourseParam;
import com.hu.qingshan.model.RequestParam.LessonParam;
import com.hu.qingshan.modules.course.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 获取所有课程
     */
    @GetMapping("/course")
    public void queryCourses(){

    }

    /**
     * 根据课程id获取课程
     * @param courseId
     */
    @GetMapping("/course/{courseId:[A-Za-z0-9_-]+}")
    public void queryCourseById(@PathVariable("courseId") String courseId){

    }

    /**
     * 获取某个课程下的具体视频课时
     * @param courseId
     * @param lessonId
     */
    @GetMapping("/course/{courseId:[A-Za-z0-9_-]+}/lesson/{lessonId:[A-Za-z0-9_-]+}")
    public void queryLessonUnderCourse(
            @PathVariable("courseId") String courseId,
            @PathVariable("lessonId") String lessonId)
    {

    }

    /**
     * 添加新课程
     */
    @PostMapping("/course")
    public String addNewCourse(@Valid @RequestBody CourseParam courseParam)
    {
        return courseService.addNewCourse(courseParam);
    }

    /**
     * 删除课程
     * @param courseId
     */
    @DeleteMapping("/course/{courseId:[A-Za-z0-9_-]+}")
    public void removeCourseById(@PathVariable("courseId") String courseId){

    }

    /**
     * 添加新章节
     */
    @PostMapping("/course/{courseId:[A-Za-z0-9_-]+}/chapter")
    public String addNewChapter(
            @PathVariable("courseId") String courseId,
            @Valid @RequestBody ChapterParam chapterParam)
    {
        return courseService.addNewChapter(chapterParam,courseId);
    }

    /**
     * 删除课程下的某个章节
     * @param chapterId
     */
    @DeleteMapping("/course/{courseId:[A-Za-z0-9_-]+}/chapter/{chapterId:[A-Za-z0-9_-]+}")
    public void remove(
            @PathVariable("courseId") String courseId,
            @PathVariable("chapterId") String chapterId)
    {

    }

    /**
     * 添加某个课程下的新课时
     */
    @PostMapping("/course/{courseId:[A-Za-z0-9_-]+}/chapter/{chapterId:[A-Za-z0-9_-]+}/lesson")
    public String addNewLesson(
            @PathVariable("courseId") String courseId,
            @PathVariable("chapterId") String chapterId,
            @Valid @RequestBody LessonParam lessonParam)
    {
        return courseService.addNewLesson(lessonParam,courseId,chapterId);
    }

    /**
     * 删除某个课程下的课时
     */
    @DeleteMapping("/course/{courseId:[A-Za-z0-9_-]+}/chapter/{chapterId:[A-Za-z0-9_-]+}/lesson/{lessonId:[A-Za-z0-9_-]+}")
    public void removeLesson(
            @PathVariable("courseId") String courseId,
            @PathVariable("chapterId") String chapterId,
            @PathVariable("lessonId") String lessonId)
    {

    }

}
