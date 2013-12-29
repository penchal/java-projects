package com.demo.web;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {

  public MyApplication() {
    packages("com.demo.web");
    register(MoxyJsonFeature.class);
    
    // This was not required
    // register(createMoxyJsonResolver());

    LoggingFilter loggingFilter = new LoggingFilter(
        Logger.getLogger(MyApplication.class.getName()), true);
    registerInstances(loggingFilter);
  }

  //  public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
  //    Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
  //    namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance",
  //        "xsi");
  //    
  //    final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig()
  //        .setNamespacePrefixMapper(namespacePrefixMapper)
  //        .setNamespaceSeparator(':');
  //    
  //    return moxyJsonConfig.resolver();
  //  }

}
