package com.spring.profiles;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration extends CommonConfiguration {

  @Qualifier("serviceImpl")
  @Bean
  public Service get() {
    return testService;
  }

}