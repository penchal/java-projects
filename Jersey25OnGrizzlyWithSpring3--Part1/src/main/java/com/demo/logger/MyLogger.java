package com.demo.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MyLogger {

  private static final Logger LOGGER = Logger.getLogger("MyLogger");

  public static void logAsInfo(Object object) {
    StackTraceElement[] stackTraceElements = Thread.currentThread()
        .getStackTrace();
    String printingClassAndMethod = stackTraceElements[2].getClassName() + "."
        + stackTraceElements[2].getMethodName() + "()";
    LOGGER.log(Level.INFO, printingClassAndMethod + ": " + object);
  }

}
