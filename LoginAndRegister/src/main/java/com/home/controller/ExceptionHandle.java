package com.home.controller;

import com.home.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandle {

//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler({Exception.class})
//    public CommonResult handleException(Exception e) {
//        log.error("[handleException] ", e);
//        return new CommonResult(500,"请求错误");
//    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({BindException.class})
//    public CommonResult handleHttpMessageNotReadableException(BindException e) {
//        BindingResult result = e.getBindingResult();
//        String message = getBindResultMessage(result);
//        log.warn("[handleHttpMessageNotReadableException] 参数绑定失败：" + message);
//        return new CommonResult(500,"参数绑定错误");
//    }



}
