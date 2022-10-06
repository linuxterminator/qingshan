package com.hu.qingshan.model.DTO;

import lombok.Data;

@Data
public class WXDTO {

    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

}
