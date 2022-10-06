package com.hu.qingshan.repository;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.mapper.ChapterMapper;
import com.hu.qingshan.mapper.CourseMapper;
import com.hu.qingshan.mapper.LessonMapper;
import com.hu.qingshan.model.DTO.*;
import com.hu.qingshan.model.DatabaseModel.Chapter;
import com.hu.qingshan.model.DatabaseModel.Course;
import com.hu.qingshan.model.DatabaseModel.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseRepository extends ModelConvert {

    private final CourseMapper courseMapper;
    private final ChapterMapper chapterMapper;
    private final LessonMapper lessonMapper;

    public CourseRepository(CourseMapper courseMapper, ChapterMapper chapterMapper, LessonMapper lessonMapper) {
        this.courseMapper = courseMapper;
        this.chapterMapper = chapterMapper;
        this.lessonMapper = lessonMapper;
    }

    /**
     * 添加课程
     * @param course
     */
    public void saveCourse(Course course){
        course.initSelfAttribute();
        courseMapper.saveCourse(course);
    }

    /**
     * 添加章节
     * @param chapter
     */
    public void saveChapter(Chapter chapter){
        chapter.initSelfAttribute();
        chapterMapper.saveChapter(chapter);
    }

    /**
     * 查询课程下的章节
     * @param courseId
     * @return
     */
    public List<BaseChapterDTO> queryChapter(String courseId){
        List<Chapter> chapters = chapterMapper.queryChaptersByCourseId(courseId);
        return ConvertToListTarget(chapters,BaseChapterDTO.class);
    }

    /**
     * 添加课时
     * @param lesson
     */
    public void saveLesson(Lesson lesson){
        lesson.initSelfAttribute();
        lessonMapper.saveLesson(lesson);
    }

    /**
     * 删除课程
     * 该接口为更新操作，需要记录更新时间
     * @param courseId
     */
    public void deleteCourse(String courseId){
        courseMapper.deleteById(courseId);
    }

    /**
     * 查询简单课程列表
     * @return
     */
    public List<SimpleCourseDTO> queryCourse(){
        List<SimpleCourseDTO> simpleCourseDTOS = ConvertToListTarget(courseMapper.queryCourse(),SimpleCourseDTO.class);

        // 需要查询simpleCourseDTO中的课时数
        simpleCourseDTOS.forEach(course->{
            Integer lessonCount = lessonMapper.queryByCourseId(course.getId()).size();
            Integer chapterCount = chapterMapper.queryChapterCountByCourseId(course.getId());
            course.setLessonCount(lessonCount);
            course.setChapterCount(chapterCount);
        });

        return simpleCourseDTOS;
    }

    /**
     * 查询复杂列表
     * @return
     */
    public List<CourseDetialDTO> queryDetialCourse(){
        // courseDetialDTO继承自simpleCourseDTO，这里直接调用queryCOurse()
        List<CourseDetialDTO> courseDetialDTOS = ConvertToListTarget(queryCourse(), CourseDetialDTO.class);

        // 查询章节
        courseDetialDTOS.forEach(courseDetialDTO -> {
            List<Chapter> chapters = chapterMapper.queryChaptersByCourseId(courseDetialDTO.getId());
            List<ChpaterDTO> chpaterDTOS = ConvertToListTarget(chapters, ChpaterDTO.class);
            courseDetialDTO.setChapterList(chpaterDTOS);

            // 查询章节下的课时
            chpaterDTOS.forEach(chapter -> {
                List<Lesson> lessons = lessonMapper.queryByCourseIdAndChapterId(courseDetialDTO.getId(),chapter.getId());
                List<LessonDTO> lessonDTOS = ConvertToListTarget(lessons, LessonDTO.class);
                chapter.setLessonList(lessonDTOS);
            });

        });

        return courseDetialDTOS;
    }

    /**
     * repo封装了一些操作给service进行调用
     */

    /**
     * 课程id是否存在
     * @return
     */
    public Boolean validateCourseId(String courseId){
        return courseMapper.isCourseIdExists(courseId) == 0;
    }

    /**
     * 章节id是否存在
     * @return
     */
    public Boolean validateChapterId(String chapterId){
        return chapterMapper.isChapterIdExists(chapterId) == 0;
    }

}
