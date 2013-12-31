package com.demo.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.beans.MyPojo.FRIEND_TYPE;

@Component
@Scope("singleton")
public class BusinessLogic {
  
  public MyPojo getTimeFor(String name, String specialMessage) {
    System.out.println("Hashcode: " + this.hashCode());
    
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    Friend friend = null;
    try {
      String classNameToLookFor = "com.demo.friends." + name + ".FriendImpl";
      friend = (Friend) Class.forName(classNameToLookFor).newInstance();
    } catch (InstantiationException | IllegalAccessException
        | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    
    friend.setName(name);
    friend.setSpecialMessage(specialMessage);
    
    MyPojo myPojo = new MyPojo(new Date(), friend.getTime());
    myPojo.setType(FRIEND_TYPE.INTIMATE);
    
    return myPojo;
  }

}
