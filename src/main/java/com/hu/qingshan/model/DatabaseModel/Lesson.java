package com.hu.qingshan.model.DatabaseModel;

import com.hu.qingshan.model.DatabaseModel.Base.BaseModel;
import lombok.Data;

@Data
public class Lesson extends BaseModel {
    // 对应的章节id
    private String chapterId;
    // 对应的课程id
    private String courseId;
    // 课程地址
    private String videoUrl;
    // 课程时长
    private Integer duration;
    // 课时名
    private String lessonName;
    // 课时时长
    private Integer lessonIndex;

    public Lesson initSelfAttribute(){
        initData();
        return this;
    }
}
