package com.hu.qingshan.model.RequestParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseParam {

    // 课程名
    @NotBlank(message = "课程名不能为空")
    private String courseName;
    // 课程描述
    @NotBlank(message = "课程描述不能为空")
    private String courseDesc;
    // 课程封面
    private String courseCover;
}
