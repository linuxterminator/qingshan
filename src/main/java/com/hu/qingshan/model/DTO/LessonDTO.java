package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDTO extends BaseDTO{
    // 课程地址
    private String videoUrl;
    // 课程时长
    private Integer duration;
}
