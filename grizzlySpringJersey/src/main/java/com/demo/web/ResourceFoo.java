package com.demo.web;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.beans.MyBean;
import com.sun.jersey.api.core.HttpContext;

@Component
@Path("/{username}")
// TODO: There is an issue. Whatever is passed as username, first letter of that string is not copied into username.
// Dont know why
public class ResourceFoo {
  
  @Autowired
  MyBean myBean;
  
  private String username;
  
  @Context
  HttpContext context;
  
  @Path("time")
  public ResourceFoo wta(@PathParam("username") String username) {
    System.out.println("\n\nPath : " + context.getUriInfo().getPath());
    
    System.out.println("\n\nPath parameters: ");
    MultivaluedMap<String, String> pathParameters = context.getUriInfo().getPathParameters();
    for (Entry<String, List<String>> e : pathParameters.entrySet()) {
      System.out.println(e.getKey() + " -- " + e.getValue());
    }
    
    System.out.println("\n\nPath segments: ");
    List<PathSegment> segments = context.getUriInfo().getPathSegments();
    for (PathSegment pathSegment : segments) {
      System.out.println(pathSegment.getPath());
    }
    
    this.username = username;
    return this;
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response status() {
    return Response.ok(myBean.getTimeBlob(username)).build();
  }

}
