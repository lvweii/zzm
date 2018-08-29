package com.zzm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * User ：
 *
 * @author Lv
 * @since 2018/8/10 17:26
 */
@Entity
@Table(name = "user")
public class User {

  private Long id;

  private String name;

  private String password;

  private Integer status;

  private String descn;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "username")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "status")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Column(name = "descn")
  public String getDescn() {
    return descn;
  }

  public void setDescn(String descn) {
    this.descn = descn;
  }
}
