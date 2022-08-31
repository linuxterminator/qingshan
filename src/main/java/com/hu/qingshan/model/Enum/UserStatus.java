package com.hu.qingshan.model.Enum;

public enum UserStatus {


    NORMAL("normal"),
    DISABLE("disable");

    private String statusDes;

    private UserStatus(String  statusDes){
        this.statusDes = statusDes;
    }

}
