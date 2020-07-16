package com.scorequery.controller;

import com.scorequery.entity.CommonResult;
import com.scorequery.entity.Facultys;
import com.scorequery.service.impl.FacultysService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@RestController
public class FacultysController {

    @Resource
    FacultysService facultysService;

    //根据学生id查询所在系
    @GetMapping(path = "/Facultys/{id}")
    public CommonResult GetFacultysById(@PathVariable("id") BigInteger FacId) {

       Facultys facultys = facultysService.GetFacultysById(FacId);

        return new CommonResult(200,"查询成功",facultys);
    }



}
