package com.hu.qingshan.model.RequestParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LessonParam {
    @NotBlank(message = "课程名")
    private String lessonName;

    /**
     * 课程序号（可选）
     */
    private Integer lessonIndex = 9999;

}
