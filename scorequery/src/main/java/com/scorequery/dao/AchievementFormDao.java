package com.scorequery.dao;

import com.scorequery.entity.Achievement;
import com.scorequery.entity.AchievementForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementFormDao {

    public List<AchievementForm> GetAchievementForm();

    public String GetAchievementFormName(@Param("semester") String Semester);

    public List<String> GetAllSemester();

    public List<String> GetAllFormName();

}
