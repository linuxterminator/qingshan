package com.hu.qingshan.model.DatabaseModel;

import com.hu.qingshan.model.DatabaseModel.Base.BaseModel;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Chapter extends BaseModel {

    // 对应的课程的id
    private String courseId;
    // 章节名
    private String chapterName;
    // 章节描述
    private String chapterDesc;
    // 章节序号
    private Integer chapterIndex;

    /**
     * 设置数据库模型默认值
     * @return
     */
    public Chapter initSelfAttribute(){
        initData();
        return this;
    }

}
