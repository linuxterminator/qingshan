package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDTO {
    // 课时id
    private String lessonId;
    // 创建时间
    private LocalDateTime createTime;
    // 更新时间
    private LocalDateTime updateTime;
    // 课程地址
    private String videoUrl;
    // 课程时长
    private Integer duration;
}
