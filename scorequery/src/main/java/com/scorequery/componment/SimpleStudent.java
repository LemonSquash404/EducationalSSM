package com.scorequery.componment;

import com.scorequery.entity.Class;
import com.scorequery.entity.Facultys;
import com.scorequery.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleStudent {

    private BigInteger studentId;
    private String studentName;
    private String facultysName;
    private String className;
    private Map<String,Object> achievement;

    public static SimpleStudent simpleAchievementFactory(Student student , Class classes, Facultys facultys,
                                                         Map<String,Object> ach) {
        SimpleStudent simpleStudentBuilder = new SimpleStudent();
        simpleStudentBuilder.studentId = student.getStudentId();
        simpleStudentBuilder.studentName = student.getStudentName();
        simpleStudentBuilder.facultysName = facultys.getFacultyName();
        simpleStudentBuilder.className = classes.getClassName();
        simpleStudentBuilder.achievement = ach;
        return simpleStudentBuilder;
    }

}
