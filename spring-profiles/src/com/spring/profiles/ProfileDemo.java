package com.spring.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ProfileDemo {

  @Autowired
  @Qualifier("serviceImpl")
  private Service service;

  @Autowired
  @Qualifier("prod")
  private Service prodService;

  @Autowired
  @Qualifier("test")
  private Service testService;

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.getEnvironment().addActiveProfile("prod");
    context.load("spring.xml");
    context.refresh();

    ProfileDemo demo = context.getBean(ProfileDemo.class);
    demo.prodService.foo();
    demo.testService.foo();
    demo.service.foo();

    context.close();
  }

}
