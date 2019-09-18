package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    //查询状态为2并以创建日期降序排序，查询前4条记录
    @GetMapping("/search/recommend")
    public List<Recruit> searchRecommend(){
        return recruitService.searchRecommend();
    }

    //查询状态不为0并以创建日期降序排序，查询前12条记录
    @GetMapping("/search/newlist")
    public List<Recruit> searchNewlist(){
        return recruitService.searchNewlist();
    }
}
