package com.demo.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyBean {
  
  public MyBlob getTimeBlob(String name) {
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    Friend friend = null;
    try {
      friend = (Friend) Class.forName("com.demo.friends." + name + ".Friend1").newInstance();
    } catch (InstantiationException | IllegalAccessException
        | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    
    return new MyBlob(new Date(), friend.getTime());
  }

}
