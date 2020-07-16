package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private BigInteger roleid;
    private String rolename;
    private String roledecribe;
}
