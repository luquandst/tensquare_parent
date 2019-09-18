package com.tensquare.qa.controller;

import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    //最新回复的问题显示在上方， 按回复时间降序排序
    @GetMapping("/newlist/{label}/{page}/{size}")
    public Result newlist(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        //根据条件进行查询
        PageResult pageResult = problemService.newlist(label, page, size);
        //返回查询结果
        return new Result(true, StatusCode.OK, "查看成功", pageResult);
    }

    //按回复数降序排序
    @GetMapping("/hotlist/{label}/{page}/{size}")
    public Result hotlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        //根据条件进行查询
        PageResult pageResult = problemService.hotlist(label, page, size);
        //返回查询结果
        return new Result(true, StatusCode.OK, "查看成功", pageResult);
    }

}