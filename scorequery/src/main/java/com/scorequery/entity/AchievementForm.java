package com.scorequery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchievementForm {

    private   String achievementDate;
    private  BigInteger createrId;
    private  String createrName;
    private  String formName;

}
