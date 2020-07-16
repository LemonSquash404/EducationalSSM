package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntryAchievementForm {

    private BigInteger facultyId;
    private String  facultyName;
    private BigInteger classId;
    private String className;
    private BigInteger studentId;
    private String studentName;
    private BigInteger courseId;
    private String courseName;
    private String achievement;
    private String semester;
    private BigInteger teacherId;
    private String teacherName;
    private List<Map<String,Object>> achievements;
}
