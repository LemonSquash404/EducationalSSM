package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInFo {

        private String name;
        private String avatar;
        private String introduction;
        private List<String> roles;
}
