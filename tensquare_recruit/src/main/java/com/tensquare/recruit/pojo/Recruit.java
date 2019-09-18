package com.tensquare.recruit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/3/23 15:58
 */
@Entity
@Table(name = "tb_recruit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruit {
    @Id
    private String id;
    private String jobname;
    private String salary;
    private String condition;
    private String education;
    private String type;
    private String address;
    private String createtime;
    private String eid;
    private String state;
    private String label;
    private String url;
    private String content1;
    private String  content2;


}
