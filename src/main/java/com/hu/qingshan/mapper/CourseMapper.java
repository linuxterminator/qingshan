package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    void saveCourse(@Param("course") Course course);

    List<Course> queryCourse();

    Integer isCourseIdExists(@Param("courseId") String courseId);

    void deleteById(@Param("courseId") String courseId);

}
