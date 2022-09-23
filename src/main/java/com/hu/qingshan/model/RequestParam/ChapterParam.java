package com.hu.qingshan.model.RequestParam;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ChapterParam {
    // 章节名
    @NotBlank(message = "章节名不能为空")
    private String chapterName;
    // 章节描述
    @NotBlank(message = "章节描述不能为空")
    private String chapterDesc;
    /**
     * 章节序号（可选）
     */
    private Integer chapterIndex = 9999;
}
