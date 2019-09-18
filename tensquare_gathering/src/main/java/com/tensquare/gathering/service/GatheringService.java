package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GatheringService {
    @Autowired
    private GatheringDao gatheringDao;

    //以id作为key，以方法的结果为返回值保存到SpringCache中
    @Cacheable(value = "gathering" ,key = "#id")
    public Gathering findById(String id){
        return gatheringDao.findById(id).get();
    }

    //更新缓存：先删除缓存，再保存到数据库
    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(Gathering gathering){
        //再保存到数据库
        gatheringDao.save(gathering);
    }

    //上面注解表示从缓存中删除key为id的缓存
    @CacheEvict(value = "gathering",key = "#id")
    public void delete(String id){
        //从数据库删除
        gatheringDao.deleteById(id);
    }
}
