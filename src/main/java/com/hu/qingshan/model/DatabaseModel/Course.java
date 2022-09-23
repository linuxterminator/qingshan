package com.hu.qingshan.model.DatabaseModel;

import com.hu.qingshan.model.DatabaseModel.Base.BaseModel;
import lombok.Data;

@Data
public class Course extends BaseModel {

    // 课程名
    private String courseName;
    // 课程描述
    private String courseDesc;
    // 课程封面
    private String courseCover;
    // 课程总时长
    private Integer courseDuration;

    /**
     * 设置数据库模型默认值
     * @return
     */
    public Course initSelfAttribute(){
        initData();
        this.courseDuration = 0;
        return this;
    }

}
