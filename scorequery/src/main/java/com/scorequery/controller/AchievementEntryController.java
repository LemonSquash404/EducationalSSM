package com.scorequery.controller;

import com.alibaba.fastjson.JSONObject;
import com.scorequery.componment.ToNull;
import com.scorequery.entity.CommonResult;
import com.scorequery.entity.Student;
import com.scorequery.service.impl.AchievementEntryServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
public class AchievementEntryController{

    @Resource
    AchievementEntryServiceImpl achievementEntryService;

    @Resource
    ToNull toNull;

    @GetMapping(path = "/GetAchievementSelectCondition")
    public CommonResult GetAchievementSelectCondition(){
     Map<String,Object> selectinfo = achievementEntryService.GetAchievementSelectCondition();
     return new CommonResult(200,"成功",selectinfo);
    }

    @GetMapping(path = "/GetAchievementForm")
    public CommonResult GetAchievementForm(@RequestParam("listQuery") String listQuery){
        //传来的数据不是integer 就是 "" 改成bigint
        Map ListQuery = (Map) JSONObject.parse(listQuery);
        Integer page = (Integer) ListQuery.get("page");
        Integer limit = (Integer) ListQuery.get("limit");
        String semesters =  toNull.NullToString(ListQuery.get("semesters").toString());
        String name = toNull.NullToString(ListQuery.get("name").toString());
        BigInteger faculty = toNull.NullToBigInteger(ListQuery.get("faculty").toString());
        BigInteger classId = toNull.NullToBigInteger(ListQuery.get("classID").toString());
        BigInteger course =  toNull.NullToBigInteger(ListQuery.get("course").toString());

        List<Object> achievementList =
                achievementEntryService.GetAchievementForm(page,limit,semesters,name,
                                                faculty, classId, course);
        return new CommonResult(200,"成功",achievementList);
    }


    @PostMapping(path = "/ChangerAchievement")
    public CommonResult ChangerAchievement(@RequestBody Map<String,Object> tempData){
        String stuId =  tempData.get("id").toString();
        String courseId = tempData.get("courseId").toString();
        String achievement = (String) tempData.get("achievement");
        String semester = (String) tempData.get("semester");
        String facultyId =  tempData.get("facultyId").toString();
        String classId =  tempData.get("classId").toString();
       boolean flag = achievementEntryService.ChangerAchievement(stuId,courseId,achievement,semester,facultyId,classId);
        if (flag){
            return new CommonResult(200,"成功");
        }else {
            return new CommonResult(500,"错误");
        }
    }


    @DeleteMapping(path = "/DeletAchievement")
    public CommonResult DeletAchievement(@RequestBody Map<String,Object> tempData){
        System.out.println(tempData);
        String stuId =  tempData.get("id").toString();
        String courseId = tempData.get("courseId").toString();
        String semester = (String) tempData.get("semester");
        String facultyId =  tempData.get("facultyId").toString();
        String classId =  tempData.get("classId").toString();
        boolean flag = achievementEntryService.DeletAchievement(stuId,courseId,semester,facultyId,classId);
        if (flag){
            return new CommonResult(200,"成功");
        }else {
            return new CommonResult(500,"错误");
        }
    }

    @PostMapping(path = "/AddAchievement")
    public CommonResult AddAchievement(@RequestBody List<Object> list){
        int num = 0;
        for (Object object : list){
        Map tempData = (Map) object;
        String stuId =  tempData.get("studentId").toString();
        String courseId = tempData.get("courseId").toString();
        String achievement = tempData.get("achievement").toString();
        String semester = (String) tempData.get("semesters");
        String facultyId =  tempData.get("facultyId").toString();
        String classId =  tempData.get("classId").toString();
        boolean flag = achievementEntryService.AddAchievement(stuId,courseId,achievement,semester,facultyId,classId);
        if (flag)num++;
        }
            return new CommonResult(200,"成功",num+"条数据写入");
    }

    @GetMapping(path = "/SearcheStudent")
    public CommonResult SearcheStudent(@RequestParam("facultyId") String facultyId,
                                       @RequestParam("classId") String classId){
        List<Student> students = achievementEntryService.SearcheStudent(facultyId,classId);
        return new CommonResult(200,"成功",students);
    }

}
