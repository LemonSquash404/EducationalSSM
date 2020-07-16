package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    private BigInteger courseId;
    private String courseName;
    private BigInteger teacherId;
    private Teacher teacher;
}
