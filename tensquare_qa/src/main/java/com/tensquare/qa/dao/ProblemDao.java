package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import entity.PageResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

    //最新回复的问题显示在上方， 按回复时间降序排序
    @Query(nativeQuery = true,value = "select p.* from tb_problem p,tb_pl where id=problemid and labelid=?1 order by createtime desc ")
    PageResult newlist(String label, Pageable pageable);

    ////按回复数降序排序
    @Query(nativeQuery = true,value = "select p.* from tb_problem p,tb_pl where id=problemid and labelid=?1 order by reply desc ")
    PageResult hotlist(String label, PageRequest of);
}
