package com.scorequery.service.impl;

import com.scorequery.dao.FacultysDao;
import com.scorequery.dao.StudentDao;
import com.scorequery.entity.Facultys;
import com.scorequery.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class FacultysService {

    @Resource
    private FacultysDao facultysDao;


    public Facultys GetFacultysById(BigInteger FacId) {
        return facultysDao.GetFacultysById(FacId);
    }

    public List<Facultys> GetAllFacultys() {
        return facultysDao.GetAllFacultys();
    }


}
