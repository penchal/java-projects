package com.spring.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

  @Autowired
  protected TestService testService;

  @Autowired
  protected ProdService prodService;

  @Qualifier("test")
  @Bean
  protected Service getTestService() {
    return testService;
  }

  @Qualifier("prod")
  @Bean
  protected Service getProdService() {
    return prodService;
  }

}
