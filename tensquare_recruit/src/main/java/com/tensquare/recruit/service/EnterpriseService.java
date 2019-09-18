package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    //查询热门企业
    public List<Enterprise> findIshot() {
        return enterpriseDao.findByIshot("1");
    }
}
