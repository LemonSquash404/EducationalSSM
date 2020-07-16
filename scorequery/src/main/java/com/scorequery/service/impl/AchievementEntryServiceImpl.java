package com.scorequery.service.impl;

import com.scorequery.dao.*;
import com.scorequery.entity.*;
import com.scorequery.entity.Class;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Service
public class AchievementEntryServiceImpl {

    @Resource
    private AchievementEntryDao achievementEntryDao;

    @Resource
    private FacultysDao facultysDao;

    @Resource
    private ClassDao classDao;

    @Resource
    private CourseDao courseDao;
    @Resource
    private AchievementFormDao achievementFormDao;

    public Map<String, Object> GetAchievementSelectCondition() {
        List<String> semesters = achievementFormDao.GetAllSemester();
        List<Facultys> facultys = facultysDao.GetAllFacultys();
        List<Class> classes = classDao.GetAllClass();
        List<Course> courses = courseDao.GetAllCourse();
        Map<String, Object> selectInfo = new HashMap<>();
        selectInfo.put("semesters", semesters);
        selectInfo.put("facultys", facultys);
        selectInfo.put("classes", classes);
        selectInfo.put("courses", courses);
        return selectInfo;
    }

    public List<Object> GetAchievementForm(Integer page, Integer limit, String semesters,
                                           String name, BigInteger facultyId, BigInteger classId,
                                           BigInteger courseId) {
        List<String> formNames = achievementEntryDao.GetAchievementFormName(semesters);
        List<String> temp = new ArrayList<>();
        List<Map<String, EntryAchievementForm>> entryList = new ArrayList<>();
        //初始化
        //去重
        for (String value : formNames) {
            boolean flag = true;
            for (String s : temp) {
                if (value.equals(s)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                temp.add(value);
            }
        }
        formNames = temp;
        Integer pos = (page - 1) * limit / formNames.size();
        for (String formName : formNames) {
            List<EntryAchievementForm> tem =
                    achievementEntryDao.GetAchievementForm(pos, limit, formName, name, facultyId, semesters, classId, courseId);
            //处理成绩表格式，让属于一个学生的成绩归到一起
            Map<String, EntryAchievementForm> tcmap = new HashMap<>();
            for (EntryAchievementForm achievementForm : tem) {
                String key = achievementForm.getStudentId().toString() + achievementForm.getSemester();
                if (tcmap.get(key) == null) {
                    achievementForm.setAchievements(new LinkedList<Map<String, Object>>());
                    tcmap.put(key, achievementForm);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("courseId", achievementForm.getCourseId());
                map.put("courseName", achievementForm.getCourseName());
                map.put("achievement", achievementForm.getAchievement());
                map.put("teacherName", achievementForm.getTeacherName());
                tcmap.get(key).getAchievements().add(map);
            }
            entryList.add(tcmap);
        }
        List<Object> achievementList = new LinkedList<>();
        for (Map<String, EntryAchievementForm> map : entryList) {
            for (String key : map.keySet()) {
                achievementList.add(map.get(key));
            }
        }
        return achievementList;
    }

    public boolean ChangerAchievement(String stuId, String courseId, String achievement, String semester, String facultyId, String classId) {
        String formName = achievementFormDao.GetAchievementFormName(semester);
        int res = achievementEntryDao.ChangerAchievement(
                new BigInteger(stuId), new BigInteger(courseId),
                new BigInteger(facultyId), new BigInteger(classId),
                achievement, formName);
        if (res >= 1) {
            return true;
        }
        return false;
    }

    public boolean DeletAchievement(String stuId, String courseId, String semester, String facultyId, String classId) {
        String formName = achievementFormDao.GetAchievementFormName(semester);
        int res = achievementEntryDao.DeletAchievement(
                new BigInteger(stuId), new BigInteger(courseId),
                new BigInteger(facultyId), new BigInteger(classId), formName);
        if (res >= 1) {
            return true;
        }
        return false;
    }

    @Transactional
    public boolean AddAchievement(String stuId, String courseId, String achievement, String semester, String facultyId, String classId) {
        String formName = achievementFormDao.GetAchievementFormName(semester);
        String stuName = achievementEntryDao.GetStudentName(new BigInteger(stuId));
        int res = achievementEntryDao.AddAchievement(
                new BigInteger(stuId), new BigInteger(courseId),
                new BigInteger(facultyId), new BigInteger(classId),
                achievement, formName,stuName,semester);
        if (res >= 1) {
            return true;
        }
        return false;

    }

    public List<Student> SearcheStudent(String facultyId, String classId) {

        return achievementEntryDao.SearcheStudent(new BigInteger(facultyId),new BigInteger(classId));
    }
}
