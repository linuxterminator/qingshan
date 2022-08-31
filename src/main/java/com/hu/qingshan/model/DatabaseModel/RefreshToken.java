package com.hu.qingshan.model.DatabaseModel;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RefreshToken {

    private String refreshToken;
    private LocalDateTime expireDate;

    public RefreshToken(){
        this.expireDate = LocalDateTime.now().plusDays(10);
        this.refreshToken = UUID.randomUUID().toString();
    }

}
