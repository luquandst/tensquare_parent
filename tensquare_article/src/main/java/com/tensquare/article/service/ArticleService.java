package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //更改点赞的数量
    public void updateThumbup(String articleid) {
        //更改点赞的数量
        articleDao.updateThumbup(articleid);
    }

    //从redis中获取数据
    public Article findById(String id){
        //从redis中获取数据
        Article article = (Article) redisTemplate.opsForValue().get(id);
        //如果redis中的数据为空时，先从数据库查询
        if (article == null){
            article = articleDao.findById(id).get();
            //在把数据设置到缓存中去
            redisTemplate.opsForValue().set(id,article,10,TimeUnit.SECONDS);
        }
        return article;
    }

    //更新缓存
    public void update(Article article){
        //清空缓存
        redisTemplate.delete(article.getId());
        //保存到数据库,查询的时候再保存到的缓存中去
        articleDao.save(article);
    }

    //删除缓存
    public void delete(String id){
        //从缓存中删除
        redisTemplate.delete(id);
        //从数据库删除
        articleDao.deleteById(id);
    }
}
