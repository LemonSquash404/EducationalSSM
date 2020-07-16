package com.scorequery.service.impl;

import com.scorequery.componment.SimpleAchievement;
import com.scorequery.componment.SimpleStudent;
import com.scorequery.dao.AchievementDao;
import com.scorequery.dao.AchievementFormDao;
import com.scorequery.dao.CourseDao;
import com.scorequery.dao.StudentDao;
import com.scorequery.entity.*;
import com.scorequery.service.AchievementService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Resource
    private AchievementDao achievementDao;

    @Resource
    private AchievementFormDao achievementFormDao;

    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;


    public List<Achievement> GetAllAchievement(String semester, BigInteger stuId){
        String FormName = achievementFormDao.GetAchievementFormName(semester);

        return achievementDao.GetAllAchievement(FormName,semester,stuId);
    }
    //获取学期，学号，课程号
    public Achievement GetOneAchievement(BigInteger semester,
                                         BigInteger stuId,
                                         BigInteger courseId){
        String sem = "achievement_"+semester;
        return achievementDao.GetOneAchievement(sem,stuId,courseId);
    }

    public List<Achievement> GetAchievementByClass(BigInteger semester,
                                                   BigInteger facultyid,
                                                   BigInteger classid){
        String sem = "achievement_"+semester;
        return achievementDao.GetAchievementByClass(sem,facultyid,classid);
    }

    public List<Achievement> GetAchievementByFaculty( String semester,
                                                      BigInteger facultyId){
        String sem = "achievement_"+semester;
       return achievementDao.GetAchievementByFaculty(semester,facultyId);
    }


    public List<Object> GetAllAchievementNosemester(BigInteger stuid) {

        List<String> semesters =  achievementFormDao.GetAllFormName();
        List<EasyAchievement> achievements = new ArrayList<>();
        int index = 0;
        //去重
        List<String> formnames = new ArrayList<>();
        for (String sem : semesters){
            boolean flag = true;
            String tem = null;
            for (String str:formnames){
                if (str.equals(sem)){
                    flag = false;
                }else {
                    tem = sem;
                }
            }
            if (flag){
                formnames.add(sem);
            }
        }

        //获取成绩
        for (String name : formnames){
            achievements.addAll(achievementDao.GetAllAchievementNosemester(name,stuid));
        }


        //将学期去重
        List<String> data = new ArrayList<>();//学期集合
        for (EasyAchievement achievement : achievements){
            boolean flag = true;
            String tem = achievement.getSemester();
            for (String string : data){
                if (achievement.getSemester().equals(string)){
                    flag = false;
                }else {
                    tem = achievement.getSemester();
                }
            }
            if (flag){
                data.add(tem);
            }

        }

        //再转换为int从小到大排序
        List<Integer> tem1 = new ArrayList<>();
        for (String da : data){
            tem1.add(Integer.parseInt(da));
        }
        Collections.sort(tem1);

        //有了有序学期表,需要课程表
        //课程表去重
        List<String> courses = new ArrayList<>();//学期集合
        for (EasyAchievement achievement : achievements){
            boolean flag = true;
            String tem = achievement.getCourseName();
            for (String string : courses){
                if (achievement.getCourseName().equals(string)){
                    flag = false;
                }else {
                    tem = achievement.getCourseName();
                }
            }
            if (flag){
                courses.add(tem);
            }
        }

        //根据课程表和学期表做课程分布表
        List<Object> list = new ArrayList<>();
        for (String course : courses){
            Map<String,Object> map = new HashMap<>();//科目
            List<Integer> scores = new ArrayList<>();
            for(Integer date : tem1){
                for(EasyAchievement achievement : achievements){
                    if (Integer.parseInt(achievement.getSemester()) == date &&
                            achievement.getCourseName().equals(course)){
                        scores.add(Integer.parseInt(achievement.getAchievement()));
                    }
                }
            }
            map.put("coursename",course);
            map.put("datas",scores);
            list.add(map);
        }

        List<Object> result = new LinkedList<>();

        //处理学期表
        List<String> semester = new ArrayList<>();
        for ( Integer tt : tem1){
            if (tt%100 < 9){
                String aa = tt/100+"学年上学期";
                semester.add(aa);
            }else {
                String aa = tt/100+"学年下学期";
                semester.add(aa);
            }
        }
        result.add(semester);
        result.add(list);
        return result;
    }


    public List<SimpleAchievement> GetAchievementByDate(BigInteger stuid, String semester) {

        String FormName = achievementFormDao.GetAchievementFormName(semester);
        List<Achievement> achievements = achievementDao.GetAllAchievement(FormName,semester,stuid);
        Student student = studentDao.GetStudentsById(stuid);
        List<SimpleAchievement> simpleAchievements = new LinkedList<>();
        for (Achievement achievement : achievements){
               Course course =  courseDao.GetCourseById(achievement.getCourseId());
                SimpleAchievement simpleAchievement =
                SimpleAchievement.SimpleAchievementFactory(
                        student.getFacultys(), student.getStuClass(), student,course,
                        achievement,achievement.getSemester(),course.getTeacher());
            simpleAchievements.add(simpleAchievement);
            }
        return simpleAchievements;

    }


    public List<Map<String,String>> GetAchievementListTitle() {
        List<Map<String,String>> infos = new LinkedList<>();
       List<String> semester =  achievementFormDao.GetAllSemester();
       for (String title : semester){
           Map<String,String> info = new HashMap<>();
           String moush = "下学期";
           if (Integer.parseInt(title)%100 < 9 ){
               moush = "上学期";
           }
           String semesters = Integer.parseInt(title)/100 +"学年"+moush;
           info.put("label",semesters);
           info.put("key",title);
           infos.add(info);
       }
        return infos;
    }


}
