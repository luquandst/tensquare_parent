package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    /**
     * 根据条件进行查询
     * @param label
     * @return
     */
    public List<Label> search(Label label) {
        //根据条件查询所有的label
        List<Label> labelList = labelDao.findAll(CreateSpecification(label));
        //返回label集合
        return labelList;
    }

    /**
     * 根据查询条件进行分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public PageResult search(Label label, int page, int size) {
        //根据传入的参数构造一个pageRequest对象
        Pageable pageRequest = new PageRequest(page-1,size);
        //根据条件进行分页查询
        Page<Label> labelPage = labelDao.findAll(CreateSpecification(label), pageRequest);
        //返回分页查询的结果
        return new PageResult(labelPage.getTotalElements(),labelPage.getContent());
    }

    /**
     * 创造查询规格
     * @param label
     * @return
     */
    private Specification<Label> CreateSpecification(Label label) {
        //创建一个接口的实例：匿名内部类
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个predicate的集合
                List<Predicate> predicateList = new ArrayList<>();
                //判断查询条件是否为空,将查询条件添加到集合中去
                if (label != null){
                    //添加labelname的查询条件
                    if (!StringUtils.isEmpty(label.getLabelname())){
                        predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));
                    }
                    //添加state的查询条件
                    if (!StringUtils.isEmpty(label.getState())){
                        predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),label.getState()));
                    }
                    //添加recommend的查询条件
                    if (!StringUtils.isEmpty(label.getRecommend())){
                        predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),label.getRecommend()));
                    }
                }
                //定义一个封装查询条件的数组
                Predicate[] predicates = new Predicate[predicateList.size()];
                //将查询条件数组封装成查询目标数组
                Predicate[] predicatesArr = predicateList.toArray(predicates);
                //进行条件查询
                System.out.println("条件查询成功");
                return criteriaBuilder.and(predicatesArr);
            }
        };
    }


}
