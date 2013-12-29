package com.demo.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.beans.MyPojo.FRIEND_TYPE;

@Component
@Scope("prototype")
public class BusinessLogic {
  
  public MyPojo getTimeBlob(String name) {
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    Friend friend = null;
    try {
      friend = (Friend) Class.forName("com.demo.friends." + name + ".FriendImpl").newInstance();
    } catch (InstantiationException | IllegalAccessException
        | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    
    friend.setName(name);
    
    MyPojo myPojo = new MyPojo(new Date(), friend.getTime());
    myPojo.setType(FRIEND_TYPE.INTIMATE);
    
    return myPojo;
  }

}
