package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    //查询热门企业
    @GetMapping("/search/hotlist")
    public List<Enterprise> findIshot(){
        return enterpriseService.findIshot();
    }
}
