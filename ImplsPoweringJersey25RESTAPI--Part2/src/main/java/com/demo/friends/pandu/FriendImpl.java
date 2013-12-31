package com.demo.friends.pandu;

import java.util.Date;

import com.demo.beans.Friend;
import com.demo.logger.MyLogger;

public class FriendImpl implements Friend {

  private String tz = "GMT";
  private String myName;
  private String specialMessage;

  @SuppressWarnings("deprecation")
  public String getTime() {
    MyLogger.logAsInfo("Request came for time");
    
    return myName + " asked time. I speak " + tz + ". Time now: "
        + (new Date()).toGMTString() + ";;;;; Special Message: "
        + specialMessage;
  }

  @Override
  public void setName(String name) {
    this.myName = name;
  }

  @Override
  public void setSpecialMessage(String specialMessage) {
    this.specialMessage = specialMessage;
  }

}
