package com.demo.web;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ApplicationHandler;

public class MyServer {

  public static final URI BASE_URI = getBaseURI();

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost/").port(3388).build();
  }

  public static void main(String[] args) throws IOException {
    //    HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,
    //        new ApplicationHandler(new MyApplication()), true,
    //        getSslEngineConfiguration());

    ApplicationHandler appHandler = new ApplicationHandler(new MyApplication());
    HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,
        appHandler, false);

    WebappContext context = new WebappContext("REST Server - Context",
        "/WEB-INF/web.xml");
    context.deploy(httpServer);
    httpServer.start();

    System.out
        .println("In order to test the server please try the following urls:");
    String[] urls = {
        "http://localhost:3388/test",
        "http://localhost:3388/pandu/time",
        // "http://localhost:3388/rakesh/time",
        "http://localhost:3388/rakesh/time/?urgency=true",
        "http://localhost:3388/rakesh/time/?urgency=true&requestMode=async"
    };
    for (String url : urls) {
      System.out.println(url);
    }
    
    System.out.println("Press enter to stop the server...");
    System.in.read();
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
