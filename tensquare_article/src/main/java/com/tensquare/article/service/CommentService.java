package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;

    //保存评论
    public void addComment(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }


    //根据文章id查询评论
    public List<Comment> findByArticleid(String articleid) {
        return commentDao.findByArticleid(articleid);
    }
}
