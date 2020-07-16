package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouterList {

    private BigInteger routerId;
    private String routername;
    private String routertem;
    private String parent;
    private List<RouterList> children;
}
