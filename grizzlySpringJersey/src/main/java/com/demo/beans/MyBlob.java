package com.demo.beans;

import java.util.Date;

public class MyBlob {
  
  private Date date;
  private String message;
  
  private MyBlob() {}

  public MyBlob(Date d, String message) {
    this.message = message;
    this.date = d;
  }

}
