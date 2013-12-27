package com.demo.friends.friend2;

import java.util.Date;

import com.demo.beans.Friend;

public class Friend2 implements Friend {
  
  private String tz = "LOCAL";
  
  public String getTime() {
    return tz + " asked time. I speak " + tz + ". Time now: " + (new Date()).toLocaleString();
  }

}
