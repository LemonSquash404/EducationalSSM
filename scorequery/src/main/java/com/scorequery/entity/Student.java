package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private BigInteger studentId;
    private String studentName;
    private BigInteger classId;
    private BigInteger facultyId;
    private Class StuClass;
    private Facultys facultys;
    private BigInteger semester;

}
