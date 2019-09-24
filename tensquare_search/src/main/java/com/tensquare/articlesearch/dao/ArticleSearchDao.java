package com.tensquare.articlesearch.dao;

import com.tensquare.articlesearch.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    //根据关键词进行分页查询
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
