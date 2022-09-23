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
    private Integer httpStatus;
    private String msg;

    public static <D> Result<D> ok(D data){
        return new Result<D>(data,HttpStatus.OK.value(),"请求成功");
    }

    public static <D> Result<D> fail(Integer code,D data){
        return new Result<D>(data,code,"请求失败");
    }

}
