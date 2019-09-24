package com.tensquare.articlesearch.service;

import com.tensquare.articlesearch.dao.ArticleSearchDao;
import com.tensquare.articlesearch.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utils.IdWorker;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;
    @Autowired
    private IdWorker idWorker;
    //添加文章
    public void addArticle(Article article) {
        //设置文章的id
        article.setId(idWorker.nextId()+"");
        //保存到索引库
        articleSearchDao.save(article);
    }

    //根据关键词分页查询
    public PageResult<Article> findByTitleOrContentLike(String keywords, int page, int size) {
        //根据关键词分页查询
        Page<Article> articlePage = articleSearchDao.findByTitleOrContentLike(keywords, keywords, PageRequest.of(page-1, size));
        //返回结果
        return new PageResult<>(articlePage.getTotalElements(),articlePage.getContent());
    }
}
