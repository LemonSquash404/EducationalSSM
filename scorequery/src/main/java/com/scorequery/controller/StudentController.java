package com.scorequery.controller;

import com.scorequery.entity.*;
import com.scorequery.service.impl.AchievementServiceImpl;
import com.scorequery.service.impl.StudentServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@RestController
public class StudentController {

    @Resource
    StudentServiceImpl studentService;

    @Resource
    AchievementServiceImpl achievementService;

    //查询学生信息
    @GetMapping(path = "/student/{id}")
    @ResponseBody
    public String GetStudentsById(@PathVariable("id") BigInteger stuId) {
        Student student =  studentService.GetStudentsById(stuId);
        CommonResult commonResult = new CommonResult(200,"查询成功",student);
        return commonResult.toString();
    }

    @GetMapping(path = "/student")
    public CommonResult GetStudentsById() {
        return new CommonResult(200,"success");
    }


    @GetMapping(path = "/GetUserTimeTable")
    public CommonResult GetStudentTimeTable(@RequestParam("id") String stuId){
        List<Map<String,String>> timeTables =  studentService.GetStudentTimeTable(new BigInteger(stuId));
        return new CommonResult(200,"成功",timeTables);
    }
}
