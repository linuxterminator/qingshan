package com.hu.qingshan.model.DTO;

import lombok.Data;
import java.util.List;

@Data
public class CourseDetialDTO extends SimpleCourseDTO{
    // 课程章节
    private List<ChpaterDTO> chapterList;
}
