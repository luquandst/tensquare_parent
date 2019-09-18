package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article> {
    //更改点赞的数量
    @Modifying
    @Query(nativeQuery = true,value = "update tb_article set thumbup = thumbup+1 where id=?1")
    void updateThumbup(String articleid);
}
