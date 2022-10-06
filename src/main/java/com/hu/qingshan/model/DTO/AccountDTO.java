package com.hu.qingshan.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountDTO {

    private String username;
    private String nickname;
    private String userId;
    private String icon;
    private Token token;

    @Data
    @AllArgsConstructor
    public static class Token{
        private String accessToken;
        private String refreshToken;
    }

}
