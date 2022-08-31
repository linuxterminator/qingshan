package com.hu.qingshan.core.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T>{

    private T data;
    private HttpStatus httpStatus;
    private String msg;

    public static <D> Result<D> ok(D data){
        return new Result<D>(data,HttpStatus.OK,"请求成功");
    }

    public static <D> Result<D> fail(D data){
        return new Result<D>(data,HttpStatus.NOT_FOUND,"请求失败");
    }

}
