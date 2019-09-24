package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;
    //最新回复的问题显示在上方， 按回复时间降序排序
    public PageResult newlist(String label, int page, int size) {
        Page<Problem> problemPage = problemDao.newlist(label, PageRequest.of(page, size));
        return new PageResult<>(problemPage.getTotalElements(),problemPage.getContent());
    }

    //按回复数降序排序
    public PageResult hotlist(String label, int page, int size) {
        Page<Problem> hotlist = problemDao.hotlist(label, PageRequest.of(page, size));
        return new PageResult(hotlist.getTotalElements(),hotlist.getContent())  ;
    }
}
