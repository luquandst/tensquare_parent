package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    //查询所有的吐槽
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    //添加吐槽
    public void addSpit(Spit spit) {
        //设置吐槽的id
        spit.set_id(idWorker.nextId()+"");
        //添加吐槽
        spitDao.save(spit);
    }

    //根据id查询吐槽信息
    public Spit findOne(String spitid) {
        return spitDao.findById(spitid).get();
    }

    //修改吐槽信息
    public void updateSpit(Spit spit, String spitid) {
        //设置吐槽id
        spit.set_id(spitid);
        //更新吐槽信息
        spitDao.save(spit);
    }

    //根据id进行删除
    public void delete(String spitid) {
        spitDao.deleteById(spitid);
    }

    //根据上级id进行查询
    public PageResult findByParentId(String parentId, int page, int size) {
        //根据上级id进行分页查询
        Page<Spit> spitPage = spitDao.findByParentid(parentId, PageRequest.of(page, size));
        return new PageResult(spitPage.getTotalElements(),spitPage.getContent()) ;
    }

    //修改点赞的数量
    public void updateThumbup(String spitid) {
        //根据id查询出当前的吐槽
        Spit spit = spitDao.findById(spitid).get();
        //获取吐槽userid
        String userid = spit.getUserid();
        //从redis数据库中获取吐槽
        System.out.println(spit.getThumbup());
        String thumbup = (String) redisTemplate.opsForValue().get("thumb_up" + userid + "_" + spitid);
        //判断redis中的点赞是否为空，为空就点赞
        if (thumbup == null){
            //重新设置点赞的数量
            spit.setThumbup(spit.getThumbup()+1);
            System.out.println(spit.getThumbup());
            //保存到数据库
            spitDao.save(spit);
            //将点赞保存到redis
            redisTemplate.opsForValue().set("thumb_up" + userid + "_" + spitid,"1");
        }

    }
}
