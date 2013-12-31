package com.demo.web;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.grizzly.http.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.beans.BusinessLogic;
import com.demo.beans.MyPojo;

@Component
@Path("/{friendName}")
public class MyResource {

  @Autowired
  BusinessLogic  businessLogic;
  
  // NOTE: Always use queryparams via injection. U ll get default value
  @DefaultValue("false")
  @QueryParam("urgency")
  private boolean urgency;
  
  @DefaultValue("NOT_PASSED")
  @QueryParam("key")
  private String key;
  
  @DefaultValue("AGNOSTIC")
  @QueryParam("requestMode")
  private RequestMode requestMode;
  
  private String friendName;

  @Context
  private UriInfo info;
  
  @Context
  private HttpContext context;

  @Path("time")
  public MyResource time(@PathParam("friendName") String friendName) {
    printContext();
    
    this.friendName = friendName;
    return this;
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response status() {
    printContext();
    
    String specialMessage = "urgency=" + urgency + "; requestMode=" + requestMode;
    MyPojo timeBlob = businessLogic.getTimeFor(friendName, specialMessage);
    ResponseBuilder builder = Response.ok(timeBlob);
    return builder.build();
  }

  private void printContext() {
    MultivaluedMap<String, String> qParams = info.getQueryParameters();
    
    System.out.println("\n\n<<<>>> CONTEXT <<<>>>");
    System.out.println("Urgency(from URL): " + qParams.getFirst("urgency"));
    System.out.println("Urgency(from injection): " + urgency);
    
    System.out.println("RequestMode(from URL): " + qParams.getFirst("requestMode"));
    System.out.println("RequestMode(from injection): " + requestMode);
    
    System.out.println("\n\nPath : " + info.getPath());
    
    System.out.println("\n\nPath parameters: ");
    MultivaluedMap<String, String> pathParameters = info.getPathParameters();
    for (java.util.Map.Entry<String, List<String>> e : pathParameters.entrySet()) {
      System.out.println(e.getKey() + " -- " + e.getValue());
    }
    
    System.out.println("\n\nPath segments: ");
    List<PathSegment> segments = info.getPathSegments();
    for (PathSegment pathSegment : segments) {
      System.out.println(pathSegment.getPath());
    }
    
    System.out.println("\n\nQuery parameters: ");
    for (Entry<String, List<String>> e : qParams.entrySet()) {
      System.out.println(e.getKey() + "---" + e.getValue());
    }
    
    System.out.println("\n\n<<<>>> END OF CONTEXT <<<>>>");
  }

}
