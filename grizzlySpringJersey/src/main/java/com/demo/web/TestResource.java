package com.demo.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.beans.BusinessLogic;

@Component
@Path("/test")
public class TestResource {

  @Autowired
  BusinessLogic bean;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt() {
    String result = null;
    try {
      result = bean.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  @Path("name")
  @Produces(MediaType.TEXT_PLAIN)
  public TestResource name() {
    return this;
  }

}
