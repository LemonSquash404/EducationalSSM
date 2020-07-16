package com.scorequery.dao;

import com.scorequery.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CourseDao {

    public Course GetCourseById(@Param("courseId") BigInteger courseId);

    public List<Course> GetAllCourse();
}
