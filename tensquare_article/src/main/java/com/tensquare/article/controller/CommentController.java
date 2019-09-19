package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    //添加评论
    @PostMapping()
    public Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    //根据文章id查找评论
    @GetMapping("/{articleid}")
    public Result findByArticleid(@PathVariable String articleid){
        //根据文章id查询评论
        List<Comment> commentList = commentService.findByArticleid(articleid);
        return new Result(true,StatusCode.OK,"查询成功",commentList);
    }


}
