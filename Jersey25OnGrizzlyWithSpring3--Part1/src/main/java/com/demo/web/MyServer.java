package com.demo.web;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ApplicationHandler;

import com.google.common.collect.Maps;

public class MyServer {

  public static final URI BASE_URI = getBaseURI();

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost/").port(3388).build();
  }

  public static void main(String[] args) throws IOException {
    deployAsGrizzlyHttpServer();
    // deployWithWebappContext();

    printServerTestUrls();
    System.out.println("\n\n\nPress enter to stop the server...");
    System.in.read();
  }

  // NOTE: DOESNT WORK
  @SuppressWarnings("unused")
  private static void deployWithWebappContext() throws IOException {
    HttpServer httpServer = new HttpServer();
    httpServer.addListener(new NetworkListener("GrizzlyServer",
        "http://localhost/", 3388));
    String servletContextName = "REST Server - Context";
    WebappContext context = new WebappContext(servletContextName,
        "/WEB-INF/web.xml");
    context.deploy(httpServer);
  }

  private static void deployAsGrizzlyHttpServer() throws IOException {
    MyApplication application = new MyApplication();
    
    Map<String, Object> properties = Maps.newHashMap();
    properties.put("jersey.config.server.tracing", org.glassfish.jersey.server.TracingConfig.ALL);
    properties.put("jersey.config.server.tracing", org.glassfish.jersey.server.TracingConfig.ALL);
    properties.put("jersey.config.server.tracing.threshold", org.glassfish.jersey.message.internal.TracingLogger.Level.VERBOSE);
    application.addProperties(properties);
    
    ApplicationHandler appHandler = new ApplicationHandler(application);
    HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,
        appHandler, false);
    httpServer.start();
  }

  private static void printServerTestUrls() {
    System.out
        .println("In order to test the server please try the following urls:");
    String[] urls = { "http://localhost:3388/test",
        "http://localhost:3388/pandu/time",
        "http://localhost:3388/rakesh/time",
        "http://localhost:3388/rakesh/time/?urgency=true",
        "http://localhost:3388/rakesh/time/?urgency=true&requestMode=async" };
    for (String url : urls) {
      System.out.println(url);
    }
  }

  //  private static SSLEngineConfigurator getSslEngineConfiguration() {
  //    SSLContextConfigurator sslContext = new SSLContextConfigurator();
  //    sslContext.setKeyStoreFile("keystore_server");
  //    sslContext.setKeyStorePass("mobolt_password");
  //    SSLEngineConfigurator sslConfig = new SSLEngineConfigurator(sslContext);
  //    sslConfig.setClientMode(false);
  //    sslConfig.setNeedClientAuth(false);
  //    return sslConfig;
  //  }

}
