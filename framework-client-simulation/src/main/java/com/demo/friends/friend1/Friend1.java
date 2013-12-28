package com.demo.friends.friend1;

import java.util.Date;

import com.demo.beans.Friend;

public class Friend1 implements Friend {
  
  private String tz = "GMT";
  
  @SuppressWarnings("deprecation")
  public String getTime() {
    return tz + " asked time. I speak " + tz + ". Time now: " + (new Date()).toGMTString();
  }

}
