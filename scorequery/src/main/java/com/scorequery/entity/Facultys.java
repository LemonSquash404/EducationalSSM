package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Facultys {

    private BigInteger facultyId;
    private String  facultyName;

}
