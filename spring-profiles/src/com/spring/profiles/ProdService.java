package com.spring.profiles;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ProdService implements Service {

  @Override
  public void foo() {
    System.out.println("This is ProdService:foo()");
  }

}
