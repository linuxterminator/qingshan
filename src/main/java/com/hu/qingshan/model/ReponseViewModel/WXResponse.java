package com.hu.qingshan.model.ReponseViewModel;

import lombok.Data;

@Data
public class WXResponse {

    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

}
