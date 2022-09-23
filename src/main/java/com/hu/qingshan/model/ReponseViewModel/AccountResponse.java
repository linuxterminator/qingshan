package com.hu.qingshan.model.ReponseViewModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountResponse {

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
