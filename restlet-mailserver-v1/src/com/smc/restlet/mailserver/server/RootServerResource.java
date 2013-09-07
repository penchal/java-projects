package com.smc.restlet.mailserver.server;

import java.util.Date;
import java.util.logging.Logger;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class RootServerResource extends ServerResource {

  private static Logger LOGGER;

  public RootServerResource() {
	setNegotiated(false);
	LOGGER = Logger.getLogger(this.getClass().getName() + "-"
	    + (new Date()).getTime());
  }

  @Override
  public void doInit() {
	LOGGER.info("doInit()");
  }

  @Override
  public void doCatch(Throwable t) {
	LOGGER.info("Exception in RootServerResource: " + t.getMessage());
  }

  @Override
  public void doRelease() {
	LOGGER.info("doRelease");
  }

  @Override
  protected Representation get() {
	LOGGER.info("get()");
	return new StringRepresentation("RootServerResource");
  }

  @Override
  protected Representation options() throws ResourceException {
	LOGGER.info("options()");
	throw new RuntimeException("Not yet implemented");
  }

}
