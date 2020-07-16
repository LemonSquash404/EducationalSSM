package com.scorequery.dao;

import com.scorequery.entity.Achievement;
import com.scorequery.entity.Student;

import com.scorequery.entity.TimeTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@Repository
public interface StudentDao {

    public Student GetStudentsById(@Param("stuid")BigInteger stuid);

    public Map<String,Object> GetEasyStudentById(@Param("stuid")BigInteger stuid);

    public Student GetStudentsByName(@Param("stuname")String stuname);

    public List<Student> GetStudentsByClass(@Param("facultyid")BigInteger facultyid,
                                              @Param("classid")BigInteger classid);

    public Student GetStudentsByFacultyId(@Param("facultyid")BigInteger facultyid);

    public List<TimeTable> GetStudentTimeTable(@Param("facultyId")BigInteger facultyId,
                                               @Param("classId")BigInteger classId);
}
