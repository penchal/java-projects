package com.demo.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
  
  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException {
    SpringApplicationContext.context = applicationContext;
  }
  
  public static <T> T getBean(Class<T> requiredType) {
    return context.getBean(requiredType);
  }

}
