package com.hu.qingshan.modules.course.service;

import com.hu.qingshan.core.Untils.OssUntil;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.Chapter;
import com.hu.qingshan.model.DatabaseModel.Course;
import com.hu.qingshan.model.DatabaseModel.Lesson;
import com.hu.qingshan.model.RequestParam.ChapterParam;
import com.hu.qingshan.model.RequestParam.CourseParam;
import com.hu.qingshan.model.RequestParam.LessonParam;
import com.hu.qingshan.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
     * 添加新学时
     * @param lessonParam
     * @param courseId
     * @param chapterId
     * @param video
     * @return
     */
    @Override
    public String addNewLesson(LessonParam lessonParam, String courseId, String chapterId) {


        Lesson lessonInDB = ConvertToTarget(lessonParam,Lesson.class);


        lessonInDB.setCourseId(courseId);

        /**
         * 暂时设置课时视频时长为0
         */
        lessonInDB.setDuration(0);

        lessonInDB.setChapterId(chapterId);
        courseRepository.saveLesson(lessonInDB);

        return lessonInDB.getId();

    }

    /**
     * 获取所有课程
     * @return
     */
    @Override
    public List<Course> listAllCourse() {
        return null;
    }

}
