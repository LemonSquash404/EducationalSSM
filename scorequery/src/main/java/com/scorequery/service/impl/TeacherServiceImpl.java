package com.scorequery.service.impl;

import com.scorequery.dao.TeacherDao;
import com.scorequery.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
@Service
public class TeacherServiceImpl {

    @Resource
    TeacherDao teacherDao;

    public Teacher GetTeacherById(BigInteger teacherId){
        return teacherDao.GetTeacherById(teacherId);
    }

}
