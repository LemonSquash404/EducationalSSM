package com.scorequery.service.impl;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.scorequery.dao.StudentDao;
import com.scorequery.entity.Achievement;
import com.scorequery.entity.Student;
import com.scorequery.entity.TimeTable;
import com.scorequery.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentdao;


    public Student GetStudentsById(BigInteger stuId) {
        return studentdao.GetStudentsById(stuId);
    }

    public List<Map<String,String>> GetStudentTimeTable(BigInteger studentId){

        Map<String,Object> map = studentdao.GetEasyStudentById(studentId);
        BigInteger classId = new BigInteger(map.get("class_id").toString());
        BigInteger facultyId = new BigInteger(map.get("faculty_id").toString());
        List<TimeTable> timeTables =  studentdao.GetStudentTimeTable(facultyId,classId);
        List<Map<String,String>> timeTableMap = new LinkedList<>();
        //初始化
        for (int i = 0 ;i <5 ;i++){
            timeTableMap.add(new HashMap<String, String>());
        }

        for (TimeTable table : timeTables){
            Map<String,String> tableList = timeTableMap.get(table.getLesson()-1);
            tableList.put("lesson",String.valueOf(table.getLesson()));
            tableList.put(table.getCycle(),table.getCourseName());
        }
      return timeTableMap;
    }
}
