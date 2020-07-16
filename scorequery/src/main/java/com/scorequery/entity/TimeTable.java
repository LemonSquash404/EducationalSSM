package com.scorequery.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeTable {
    private BigInteger facultyId;
    private BigInteger classId;
    private BigInteger courseId;
    private String courseName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;
    private String cycle;
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    private int lesson;
}
