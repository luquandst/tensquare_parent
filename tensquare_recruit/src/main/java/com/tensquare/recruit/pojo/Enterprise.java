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
 * @Date: Create in 2019/3/23 15:37
 */
@Entity
@Table(name = "tb_enterprise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    @Id
    private String id;
    private String name;
    private String summary;
    private String address;
    private String labels;
    private String coordinate;
    private String ishot;
    private String logo;
    private String jobcount;
    private String url;

}
