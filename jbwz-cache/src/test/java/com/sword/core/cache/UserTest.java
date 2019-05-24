package com.jbwz.core.cache;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public class UserTest implements Serializable {

  private String name;
  private Integer id;

  public UserTest() {
  }

  public UserTest(String name, Integer id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String toString() {
    return JSONObject.toJSONString(this);
  }
}
