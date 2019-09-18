package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;

    //查询状态为2并以创建日期降序排序，查询前4条记录
    public List<Recruit> searchRecommend() {
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");
    }

    //查询状态不为0并以创建日期降序排序，查询前12条记录
    public List<Recruit> searchNewlist() {
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
