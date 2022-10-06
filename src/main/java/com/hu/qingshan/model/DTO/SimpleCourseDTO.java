package com.hu.qingshan.model.DTO;

import lombok.Data;

@Data
public class SimpleCourseDTO extends BaseCourseDTO{

    // 课时数目
    private Integer lessonCount;

    // 章节数目
    private Integer chapterCount;

}
