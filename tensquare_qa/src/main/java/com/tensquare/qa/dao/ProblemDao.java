package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import entity.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

   /* //最新回复的问题显示在上方， 按回复时间降序排序
    @Query(nativeQuery = true,value = "select p.* from tb_problem p,tb_pl where id=problemid and labelid=?1 order by createtime desc ")
    PageResult newlist(String label, Pageable pageable);*/
   //1.查询最新问题列表
   @Query(nativeQuery = true,value = "select * from tb_problem p,tb_pl pl where problemid=id and labelid=?1 order by createtime desc")
   Page<Problem> newlist(String labelid, Pageable pageable);

    ////按回复数降序排序
    @Query(nativeQuery = true,value = "select p.* from tb_problem p,tb_pl where id=problemid and labelid=?1 order by reply desc ")
    Page<Problem> hotlist(String label, Pageable pageable);
}
