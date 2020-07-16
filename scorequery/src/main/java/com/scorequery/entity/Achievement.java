package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Achievement {

    private BigInteger facultyId;
    private BigInteger classId;
    private BigInteger studentId;
    private BigInteger courseId;
    private String achievement;
    private String semester;
}
