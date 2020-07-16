package com.scorequery.dao;

import com.scorequery.entity.Class;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ClassDao {

    public List<Class> GetAllClass();

    public Class GetClassByName(@Param("classname") String classname);

    public Class GetClass(@Param("facultyid")BigInteger facultyid,
                          @Param("classid")BigInteger classid);

    public Class GetClassById(@Param("facultyid")BigInteger facultyid,
                              @Param("classid")BigInteger classid);
}
