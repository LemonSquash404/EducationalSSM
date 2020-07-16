package com.scorequery.service.impl;

import com.scorequery.dao.ClassDao;
import com.scorequery.dao.StudentDao;
import com.scorequery.entity.Class;
import com.scorequery.entity.CommonResult;
import com.scorequery.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class ClassServiceImpl {

    @Resource
    ClassDao classDao;

    @Resource
    StudentDao studentDao;

    public List<Class> GetAllClass(){
        return classDao.GetAllClass();
    }

    public Class GetClassByName(String classname){
        return classDao.GetClassByName(classname);
    }
    //根据班级id查询必须有系id
    public Class GetClass(BigInteger facultyid,BigInteger classname){
        return classDao.GetClass(facultyid,classname);
    }

    //根据系id 和班级id确定班级所有学生
    public List<Student> GetClassStudent( BigInteger facultyid,
                                          BigInteger classname){
        return studentDao.GetStudentsByClass(facultyid,classname);
    }
}
