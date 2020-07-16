package com.home.controller.compoment;

import com.home.entity.Router;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class RouterClone implements Serializable {

    private BigInteger rolesId;
    private String name;
    private String routertem;
    private String parent;
    private List<Router> children;

}
