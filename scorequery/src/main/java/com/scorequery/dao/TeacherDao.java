package com.scorequery.dao;

import com.scorequery.entity.Teacher;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.math.BigInteger;

@Repository
public interface TeacherDao {

    public Teacher GetTeacherById(BigInteger teacherId);

}
