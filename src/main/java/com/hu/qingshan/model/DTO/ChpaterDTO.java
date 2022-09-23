package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChpaterDTO {

    // 章节id
    private String chapterId;
    // 创建时间
    private LocalDateTime createTime;
    // 更新时间
    private LocalDateTime updateTime;
    // 章节名
    private String chapterName;
    // 章节描述
    private String chapterDesc;
    // 章节下的课程
    private List<LessonDTO> lessonList;

}
