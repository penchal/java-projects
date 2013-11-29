package com.spring.profiles;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfiguration extends CommonConfiguration {

  @Bean
  @Qualifier("theDb")
  public Database getTheDb() {
    return prodDb;
  }

}
