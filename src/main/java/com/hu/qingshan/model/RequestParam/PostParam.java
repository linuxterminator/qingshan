package com.hu.qingshan.model.RequestParam;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostParam {

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotBlank(message = "作者不能为空")
    private String author;

    @Size(min = 0,max = 2,message = "标签数最大为2")
    private List<Tag> tagList;

    @Data
    public static class Tag{
        private String tagName;
    }

}
