package com.tensquare.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
  @Id
  private String id;
  private String problemid;
  private String content;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;
  private String userid;
  private String nickname;


}
