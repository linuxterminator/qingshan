package com.hu.qingshan.model.RequestParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupParam {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickname;

}
