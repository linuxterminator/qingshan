package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChpaterDTO extends BaseChapterDTO{
    // 章节下的课程
    private List<LessonDTO> lessonList;

}
