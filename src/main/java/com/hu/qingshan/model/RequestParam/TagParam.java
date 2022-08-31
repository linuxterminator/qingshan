package com.hu.qingshan.model.RequestParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class TagParam {

    @NotBlank(message = "标签名不能为空")
    private String tagName;

    private LocalDateTime createTime;

}
