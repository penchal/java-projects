package com.smc.winetasting;

import com.smc.utils.BloomLogger;

public class WineTasting {

  public static void main(String[] args) {
    BloomLogger.log("Begin WineTasting");

    WineMatcher matcher = new WineMatcher("person_wine_3.txt");
    try {
      matcher.performMapping();
    } catch (Exception e) {
      BloomLogger.log("Unexpected exception occured. Pls check the logs.", e);
    }
  }

}
