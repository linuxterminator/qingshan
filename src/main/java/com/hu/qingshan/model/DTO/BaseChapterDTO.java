package com.hu.qingshan.model.DTO;

import lombok.Data;

@Data
public class BaseChapterDTO extends BaseDTO{
    // 章节名
    private String chapterName;
    // 章节描述
    private String chapterDesc;
    // 章节序号
    private String chapterIndex;
}
