package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Class {

    private BigInteger classId ;
    private String className;
    private BigInteger teacherId;
    private BigInteger facultyId;
    private Teacher teacher;
    private Facultys faculty;


}
