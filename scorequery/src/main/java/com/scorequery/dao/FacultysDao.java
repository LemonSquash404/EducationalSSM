package com.scorequery.dao;

import com.scorequery.entity.Facultys;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface FacultysDao {

    public Facultys GetFacultysById(BigInteger facultyId);

    public List<Facultys> GetAllFacultys();
}
