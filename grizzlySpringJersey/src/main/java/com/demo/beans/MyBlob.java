package com.demo.beans;

import java.util.Date;

public class MyBlob {
  
  private Date date;
  
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

  private String message;
  
  private MyBlob() {}

  public MyBlob(Date d, String message) {
    this.message = message;
    this.date = d;
  }

}
