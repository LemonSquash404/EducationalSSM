package com.scorequery.dao;

import com.scorequery.entity.Achievement;


import com.scorequery.entity.EasyAchievement;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.ObjectHelper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface AchievementDao {
    public List<Achievement> GetAllAchievement(@Param("formname")String formName,
                                                @Param("semester")String semester,
                                               @Param("stuId") BigInteger stuId);

    public Achievement GetOneAchievement(@Param("semester")String semester,
                                         @Param("stuId") BigInteger stuId,
                                         @Param("courseId") BigInteger courseId);

    public List<Achievement> GetAchievementByClass(@Param("semester") String semester,
                                                   @Param("facultyId")BigInteger facultyId,
                                                   @Param("classId")BigInteger classId);

    public List<Achievement> GetAchievementByFaculty(@Param("semester") String semester,
                                                     @Param("facultyId")BigInteger facultyId);

    public Map<Integer,Object> GetAchievementByDate(@Param("date")String semester,
                                                    @Param("stuid")BigInteger stuid);

    public List<EasyAchievement> GetAllAchievementNosemester(@Param("formname")String formName,
                                                             @Param("stuId") BigInteger stuId);

}
