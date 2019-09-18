package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有的标签
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 添加标签
     * @param label
     */
    public void addLabel(Label label) {
        //生成随机的id
        label.setId(idWorker.nextId()+"");
        //保存到数据库
        labelDao.save(label);
    }

    /**
     * 根据id机型查询
     * @param id
     * @return
     */
    public Label findOne(String id) {
        Label label = labelDao.findById(id).get();
        return label;
    }

    /**
     * 更新标签
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据id进行删除
     * @param id
     */
    public void delete(String id) {
        labelDao.deleteById(id);
    }
}
