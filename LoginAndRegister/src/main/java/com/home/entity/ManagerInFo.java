package com.home.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.dc.pr.PRError;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerInFo {
    private String Managername;
    private String avatar;
    private String introduction;
    private List<Object> roles;
}
