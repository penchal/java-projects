package com.demo.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.beans.MyBean;
import com.demo.beans.MyBlob;

@Component
@Path("/{username}")
// TODO: There is an issue. Whatever is passed as username, first letter of that string is not copied into username.
// Dont know why
public class ResourceFoo {

  @Autowired
  MyBean         myBean;

  private String username;

  //    @Context
  //    HttpContext context;

  @Path("time")
  public ResourceFoo wta(@PathParam("username") String username) {
    // printContext();

    this.username = username;
    return this;
  }

  //  private void printContext() {
  //    System.out.println("\n\nPath : " + context.getUriInfo().getPath());
  //    
  //    System.out.println("\n\nPath parameters: ");
  //    MultivaluedMap<String, String> pathParameters = context.getUriInfo().getPathParameters();
  //    for (Entry<String, List<String>> e : pathParameters.entrySet()) {
  //      System.out.println(e.getKey() + " -- " + e.getValue());
  //    }
  //    
  //    System.out.println("\n\nPath segments: ");
  //    List<PathSegment> segments = context.getUriInfo().getPathSegments();
  //    for (PathSegment pathSegment : segments) {
  //      System.out.println(pathSegment.getPath());
  //    }
  //  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response status() {
    MyBlob timeBlob = myBean.getTimeBlob(username);
    ResponseBuilder builder = Response.ok(timeBlob);
    return builder.build();
  }

}
