package com.scorequery.controller;

import com.scorequery.componment.SimpleAchievement;
import com.scorequery.componment.SimpleStudent;
import com.scorequery.entity.Achievement;
import com.scorequery.entity.CommonResult;
import com.scorequery.entity.EasyAchievement;
import com.scorequery.service.impl.AchievementServiceImpl;
import com.scorequery.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
public class AchievementController {

    @Resource
    StudentServiceImpl studentService;

    @Resource
    AchievementServiceImpl achievementService;

    //获取成绩 单科/全科查询
    @GetMapping("/achievement/{semester}/{stuId}")
    public CommonResult GetAllAchievement(@PathVariable("semester") String semester,
                                          @PathVariable("stuId") BigInteger stuId) {
        List<Achievement> achievementList = achievementService.GetAllAchievement(semester, stuId);
        return new CommonResult(200, "查询成功", achievementList);
    }

    //获取学期，学号，课程号
    @GetMapping("/achievement/{semester}/{stuId}/{courseId}")
    public CommonResult GetOneAchievement(@PathVariable("semester") BigInteger semester,
                                          @PathVariable("stuId") BigInteger stuId,
                                          @PathVariable("courseId") BigInteger courseId) {
        Achievement data = achievementService.GetOneAchievement(semester, stuId, courseId);
        return new CommonResult(200, "查询成功", data);
    }
    //获取成绩表头
    @GetMapping(path = "/achievement/listTitle")
    public CommonResult GetAchievementListTitle(){
        List<Map<String,String>> data =  achievementService.GetAchievementListTitle();
        return new CommonResult(200,"成功",data);
    }

    //根据学生id查询当前学期成绩
    @GetMapping(path = "/achievement/date")
    public CommonResult GetAchievementByDate(@RequestParam("stuid") BigInteger stuid,
                                             @RequestParam("page") int page,
                                             @RequestParam("limit") int limit,
                                             @RequestParam("type") String semester,
                                             @RequestParam("sort") String sort) {
        List<SimpleAchievement> data = achievementService.GetAchievementByDate(stuid,semester);

        if (data != null) {
            return new CommonResult(200, "查询成功", data);
        } else {
            return new CommonResult(200, "查询失败");
        }

    }

    //根据学生id查询所有时期成绩
    @GetMapping(path = "/achievement/Havealltime")
    public CommonResult GetAllAchievementNosemester(/*@PathVariable("id") BigInteger stuid*/) {
        BigInteger stuid = new BigInteger("201804122131");
        List<Object> data = achievementService.GetAllAchievementNosemester(stuid);
        return new CommonResult(200, "成功",data);
    }

    //查询全班成绩 需要 时期 系id 班级id
    @GetMapping(path = "/achievement/class/{semester}/{facultyid}/{classid}")
    public CommonResult GetAchievementByClass(@PathVariable("semester") BigInteger semester,
                                              @PathVariable("facultyid") BigInteger facultyid,
                                              @PathVariable("classid") BigInteger classid) {

        List<Achievement> data = achievementService.GetAchievementByClass(semester, facultyid, classid);
        CommonResult commonResult = new CommonResult(200, "查询成功", data);

        return new CommonResult(200, "查询成功", data);
    }

    //查询全系的成绩 输入的是要查询的系
    @GetMapping(path = "/achievement/faculty/{semester}/{facultyId}")
    public CommonResult GetAchievementByFaculty(@PathVariable("semester") String Semester,
                                                @PathVariable("facultyId") BigInteger facultyId) {
        List<Achievement> data = achievementService.GetAchievementByFaculty(Semester, facultyId);

        return new CommonResult(200, "查询成功", data);
    }


    //查询全系的成绩 输入的是要查询的系
    @GetMapping(path = "/achievement/test")
    public void Test() {
        System.out.println("11111111111111");
    }

}
