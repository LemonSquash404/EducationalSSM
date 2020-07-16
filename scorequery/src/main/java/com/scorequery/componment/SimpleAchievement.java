package com.scorequery.componment;

import com.scorequery.entity.*;
import com.scorequery.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sun.dc.pr.PRError;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleAchievement {

    private String facultyName;
    private String clasName;
    private String studentName;
    private String courseName;
    private String achievement;
    private String semester;
    private String teacherName;

    public static SimpleAchievement SimpleAchievementFactory(
            Facultys facultys, Class clas, Student student,
            Course course ,Achievement achievement,String semester,Teacher teacher) {
        SimpleAchievement simpleAchievement = new SimpleAchievement();
        String moush = "下学期";
        if (Integer.parseInt(semester)%100 < 9 ){
            moush = "上学期";
        }
        String semesters = Integer.parseInt(semester)/100 +"学年"+moush;
        simpleAchievement.semester = semesters;
        simpleAchievement.facultyName = facultys.getFacultyName();
        simpleAchievement.clasName = clas.getClassName();
        simpleAchievement.studentName = student.getStudentName();
        simpleAchievement.courseName = course.getCourseName();
        simpleAchievement.achievement = achievement.getAchievement();
        simpleAchievement.teacherName = teacher.getTeacherName();
        return simpleAchievement;
    }
}
