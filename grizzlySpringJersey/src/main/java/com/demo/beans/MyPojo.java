package com.demo.beans;

import java.util.Date;

public class MyPojo {
  
  private Date date;
  
  private String message;
  
  private FRIEND_TYPE type;
  
  // NOTE: I tried commenting this; But this blasted! This is very much required
  private MyPojo() {}

  public MyPojo(Date d, String message) {
    this.message = message;
    this.date = d;
  }

  // NOTE: getters and setters are also mandatory
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }

  
  public String getMessage() {
    return message;
  }

  
  public void setMessage(String message) {
    this.message = message;
  }


  public FRIEND_TYPE getType() {
    return type;
  }


  public void setType(FRIEND_TYPE type) {
    this.type = type;
  }


  public enum FRIEND_TYPE {
    INTIMATE, CLOSE;
  }

}
