package com.hu.qingshan.model.DatabaseModel;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RefreshToken {

    private String refreshToken;
    private LocalDateTime expireDate;

    private String userId;

    public RefreshToken initExpire(){
        this.expireDate = LocalDateTime.now().plusDays(10);
        this.refreshToken = UUID.randomUUID().toString();
        return this;
    }

}
