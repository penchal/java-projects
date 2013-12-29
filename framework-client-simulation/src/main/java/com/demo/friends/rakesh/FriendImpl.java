package com.demo.friends.rakesh;

import java.util.Date;

import com.demo.beans.Friend;

public class FriendImpl implements Friend {

  private String tz = "LOCAL";
  private String myName;
  private String specialMessage;

  public String getTime() {
    return myName + " asked time. I speak " + tz + ". Time now: "
        + (new Date()).toString() + ";;;;; Special Message: "
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
