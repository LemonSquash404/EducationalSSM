package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String info;
    private T      data;

    public CommonResult(Integer code, String info) {
        this.code = code;
        this.info = info;
    }


}
