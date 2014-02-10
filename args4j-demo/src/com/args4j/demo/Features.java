package com.args4j.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Features {

  @Option(name = "--enableAuth", required = true, aliases = { "-enableAuth" }, usage = "Enable/Disable authentication")
  public boolean enableAuth = true;

  public Features(String... args) {
    CmdLineParser parser = new CmdLineParser(this);

    try {
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      Logger.getGlobal().log(Level.SEVERE,
          "Unable to parse command line arguments... Quitting");
      System.exit(1);
    }
  }

  public boolean isEnableAuth() {
    return enableAuth;
  }

  public void setEnableAuth(boolean enableAuth) {
    this.enableAuth = enableAuth;
  }

}
