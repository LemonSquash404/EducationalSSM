package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {

    private BigInteger teacherId;
    private String teacherName;
    private String teacherPhone;
    private String teacherInfo;

}
