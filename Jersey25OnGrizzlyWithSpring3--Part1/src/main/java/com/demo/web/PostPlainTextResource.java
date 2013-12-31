package com.demo.web;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

//THIS WORKS
@Component
@Path("/postplaintext")
public class PostPlainTextResource {
  
  private Map<String, ?> map;

  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public Response process(String text) {
    return (Response.ok(text)).build();
  }

}
