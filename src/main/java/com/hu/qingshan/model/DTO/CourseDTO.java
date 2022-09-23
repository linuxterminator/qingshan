package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDTO {
    // 课程id
    private String courseId;
    // 创建时间
    private LocalDateTime createDate;
    // 更新时间
    private LocalDateTime updateDate;
    // 课程名
    private String courseName;
    // 课程描述
    private String courseDesc;
    // 课程封面
    private String courseCover;
    // 课程总时长
    private Integer courseDuration;

    // 课程章节
    private List<ChpaterDTO> chapterList;
}
