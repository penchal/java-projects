package com.demo.web;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

// THIS DOESNT WORK
@Component
@Path("/postjson")
public class PostJsonResource {
  
  private Map<String, Object> map;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response process(Map<String, Object> map) {
    this.map = map;
    return (Response.ok(map)).build();
  }

}
