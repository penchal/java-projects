package com.demo.beans;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// Adding the below annotation doesnot help
// @JsonIgnoreProperties
public class MyBlob {
  
  private Date date;
  private String message;

  public MyBlob(Date d, String message) {
    this.message = message;
    this.date = d;
  }

}
