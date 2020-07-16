package com.staticresource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> {

    private Integer code;
    private String info;
    private T      data;

    public CommonResult(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
