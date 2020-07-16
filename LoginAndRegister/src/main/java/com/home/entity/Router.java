package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Router implements Serializable {

    private BigInteger rolesId;
    private String name;
    private String routertem;
    private String parent;
    private List<Router> children;

}
