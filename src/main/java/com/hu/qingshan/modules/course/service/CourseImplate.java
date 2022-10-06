package com.hu.qingshan.modules.course.service;

import com.hu.qingshan.core.Untils.OssUntil;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DTO.BaseChapterDTO;
import com.hu.qingshan.model.DTO.CourseDetialDTO;
import com.hu.qingshan.model.DTO.SimpleCourseDTO;
import com.hu.qingshan.model.DatabaseModel.Chapter;
import com.hu.qingshan.model.DatabaseModel.Course;
import com.hu.qingshan.model.DatabaseModel.Lesson;
import com.hu.qingshan.model.RequestParam.ChapterParam;
import com.hu.qingshan.model.RequestParam.CourseParam;
import com.hu.qingshan.model.RequestParam.LessonParam;
import com.hu.qingshan.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseImplate extends ModelConvert implements CourseService {

    private final OssUntil oss;
    private final CourseRepository courseRepository;

    public CourseImplate(OssUntil oss, CourseRepository courseRepository) {
        this.oss = oss;
        this.courseRepository = courseRepository;
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @Override
    public String removeCourse(String courseId) {
        courseRepository.deleteCourse(courseId);
        return courseId;
    }

    /**
     * 添加课程
     * @param courseParam
     * @return
     */
    @Override
    public String addNewCourse(CourseParam courseParam){

        Course courseInDB = ConvertToTarget(courseParam,Course.class);

        courseRepository.saveCourse(courseInDB);

        return courseInDB.getId();

    }

    /**
     * 添加新章节
     * @param chapterParam
     * @return
     */
    @Override
    public String addNewChapter(ChapterParam chapterParam,String courseId) {

        Chapter chapterInDB = ConvertToTarget(chapterParam,Chapter.class);

        chapterInDB.setCourseId(courseId);

        courseRepository.saveChapter(chapterInDB);

        return chapterInDB.getId();

    }

    /**
     * 获取课程列表
     * @return
     */
    @Override
    public List<SimpleCourseDTO> querySimpleCourse() {
        return courseRepository.queryCourse();
    }

    /**
     * 获取课程下的章节
     * @return
     */
    @Override
    public List<BaseChapterDTO> queryBaseChapter(String courseId) {
        return courseRepository.queryChapter(courseId);
    }

    /**
     * 获取详细课程列表
     * @return
     */
    @Override
    public List<CourseDetialDTO> queryDetialCourse() {
        return courseRepository.queryDetialCourse();
    }

    /**
     * 添加新学时
     * @param lessonParam
     * @param courseId
     * @param chapterId
     * @return
     */
    @Override
    public String addNewLesson(LessonParam lessonParam, String courseId, String chapterId) {

        if(courseRepository.validateCourseId(courseId)){
            throw new RuntimeException("课程id不存在");
        }

        if(courseRepository.validateChapterId(chapterId)){
            throw new RuntimeException("章节id不存在");
        }

        Lesson lessonInDB = ConvertToTarget(lessonParam,Lesson.class);

        lessonInDB.setCourseId(courseId);
        lessonInDB.setChapterId(chapterId);

        /**
         * 暂时设置课时视频时长为0
         */
        lessonInDB.setDuration(0);

        courseRepository.saveLesson(lessonInDB);

        return lessonInDB.getId();

    }

}
