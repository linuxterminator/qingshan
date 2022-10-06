package com.hu.qingshan.model.DTO;

import lombok.Data;

@Data
public class BaseCourseDTO extends BaseDTO{
    // 课程名
    private String courseName;
    // 课程描述
    private String courseDesc;
    // 课程封面
    private String courseCover;
    // 课程总时长
    private Integer courseDuration;
}
