package com.hu.qingshan.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
