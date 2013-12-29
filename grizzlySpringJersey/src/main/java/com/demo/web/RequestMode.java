package com.demo.web;

public enum RequestMode {
  
  SYNC, ASYNC, AGNOSTIC;
  
  public static RequestMode fromString(String s) {
    for (RequestMode thisRequestMode: RequestMode.values()) {
      if (thisRequestMode.toString().equalsIgnoreCase(s)) {
        return thisRequestMode;
      }
    }
    
    return RequestMode.AGNOSTIC;
  }

}
