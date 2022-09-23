package com.hu.qingshan.core.ExceptionAndEnums;

public enum AccountExceptionEnums {

    USERNAME_OR_PASSWORD_VERIFY(4000,"用户名或密码错误"),
    USERNAME_ALREADY_EXISTS(4001,"用户名已存在"),
    REFRESHTOKEN_EXPIRE(4003,"refreshtoken过期，请重新登陆"),
    PLEASE_LOGIN(4004,"请先登陆"),
    ALREADY_LOGIN(4005,"已登陆，不要重复登陆");

    private Integer code;
    private String desc;

    AccountExceptionEnums(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    // 为什么这里不是void返回类型
    public ApplicationException getException(){
        throw new ApplicationException(this.desc,this.code);
    }

}
