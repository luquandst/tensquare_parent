package com.tensquare.article.controller;

import com.tensquare.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //更改文章的点赞数量
    @PutMapping("/thumbup/{articleid}")
    public Result updateThumbup(@PathVariable String articleid){
        articleService.updateThumbup(articleid);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
