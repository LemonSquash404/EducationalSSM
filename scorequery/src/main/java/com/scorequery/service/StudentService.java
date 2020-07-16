package com.scorequery.service;

import com.scorequery.entity.Student;

import java.math.BigInteger;

public interface StudentService {

    public Student GetStudentsById(BigInteger stuId);

}
