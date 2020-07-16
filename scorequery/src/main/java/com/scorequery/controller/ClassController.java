package com.scorequery.controller;

import com.scorequery.entity.Class;
import com.scorequery.entity.CommonResult;
import com.scorequery.entity.Facultys;
import com.scorequery.entity.Student;
import com.scorequery.service.impl.ClassServiceImpl;
import com.scorequery.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;


@RestController
public class ClassController {

    @Resource
    ClassServiceImpl classService;

    //查询所有班级
    @GetMapping(path = "/Class")
    public CommonResult GetClassById(){
       List<Class> data =  classService.GetAllClass();
        return new CommonResult(200,"查询成功",data);
    }

    //查询班级id
    @GetMapping(path = "/Class/name/{name}")
    public CommonResult GetClassByName(@PathVariable("name") String classname){
        Class data =  classService.GetClassByName(classname);
        return new CommonResult(200,"查询成功",data);
    }

    //查询班级name 需要指明哪个系的班级
    @GetMapping(path = "/Class/id/{facultyid}/{classid}")
    public CommonResult GetClassById(@PathVariable("facultyid") BigInteger facultyid,
                                     @PathVariable("classid") BigInteger classid){

        Class data =  classService.GetClass(facultyid,classid);
        return new CommonResult(200,"查询成功",data);
    }

    //根据系id和班级id查询班级所有学生
    @GetMapping(path = "/Class/student/{facultyid}/{classid}")
    public CommonResult GetClassStudent(@PathVariable("facultyid") BigInteger facultyid,
                                        @PathVariable("classid") BigInteger classid){

        List<Student> data =  classService.GetClassStudent(facultyid,classid);

        return new CommonResult(200,"查询成功",data);
    }


}
