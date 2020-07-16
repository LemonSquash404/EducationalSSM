package com.scorequery.dao;

import com.scorequery.entity.Achievement;
import com.scorequery.entity.CommonResult;
import com.scorequery.entity.EntryAchievementForm;
import com.scorequery.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public interface AchievementEntryDao {

    public List<EntryAchievementForm> GetAchievementForm(@Param("pos") Integer pos,
                                                         @Param("limit") Integer limit,
                                                         @Param("formName") String formName,
                                                         @Param("name") String name,
                                                         @Param("faculty") BigInteger facultyId,
                                                         @Param("semesters") String semesters,
                                                         @Param("classId") BigInteger classId,
                                                         @Param("courseid") BigInteger courseId);

    public String GetStudentName(@Param("stuId") BigInteger stuId);

    public List<String> GetAchievementFormName(@Param("semesters") String semesters);

    public int ChangerAchievement(@Param("stuId") BigInteger stuId,
                                  @Param("courseId") BigInteger courseId,
                                  @Param("facultyId") BigInteger facultyId,
                                  @Param("classId") BigInteger classId,
                                  @Param("achievement") String achievement,
                                  @Param("formName") String formName);

    public int DeletAchievement(@Param("stuId") BigInteger stuId,
                                @Param("courseId") BigInteger courseId,
                                @Param("facultyId") BigInteger facultyId,
                                @Param("classId") BigInteger classId,
                                @Param("formName") String formName);

    public int AddAchievement(@Param("stuId") BigInteger stuId,
                              @Param("courseId") BigInteger courseId,
                              @Param("facultyId") BigInteger facultyId,
                              @Param("classId") BigInteger classId,
                              @Param("achievement") String achievement,
                              @Param("formName") String formName,
                              @Param("stuName") String stuName,
                              @Param("semester") String semester);

    public List<Student> SearcheStudent(@Param("facultyId")BigInteger facultyId, @Param("classId")BigInteger classId);
}
