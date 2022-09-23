package com.hu.qingshan.core.ExceptionAndEnums;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{

    private String message;
    private Integer code;

    public ApplicationException(String message,Integer code) {
        super();
        this.message = message;
        this.code = code;
    }

}
