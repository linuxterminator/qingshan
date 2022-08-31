package com.hu.qingshan.model.ReponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountResponse {

    private String username;
    private String nickname;
    private String icon;
    private Token token;

    @Data
    @AllArgsConstructor
    public static class Token{
        private String accessToken;
        private String refreshToken;
    }

}
