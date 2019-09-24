package com.tensquare.articlesearch.controller;

import com.tensquare.articlesearch.pojo.Article;
import com.tensquare.articlesearch.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    //添加文章
    @PostMapping
    public Result addArticle(@RequestBody Article article){
        //添加文章
        articleSearchService.addArticle(article);
        return new Result(true,StatusCode.OK,"添加文章成功");
    }

    //根据关键词分页查询
    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result searchByPage(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){
        //根据关键词分页查询
        PageResult<Article> pageResult = articleSearchService.findByTitleOrContentLike(keywords,page,size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
