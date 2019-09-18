package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *  JpaRepository<Label,String> 第一个参数表示操作的实体类，第二个参数表示主键的类型
 *  JpaSpecificationExecutor<Label> 表示复杂的查询
 */
public interface LabelDao extends JpaRepository<Label,String> ,JpaSpecificationExecutor<Label> {
}
