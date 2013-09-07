package com.smc.restlet.mailserver.server;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class MailServerApplication extends Application {
  
  public static void main(String[] args) throws Exception {
	Server mailServer = new Server(Protocol.HTTP, 8111);
	mailServer.setNext(new MailServerApplication());
	
	mailServer.setName("My Mail Server");
	mailServer.setAuthor("smc");
	
	mailServer.start();
  }
  
  @Override
  public Restlet createInboundRoot() {
	Context context = getContext();

	AdminFilterRestlet adminFilterRestlet = new AdminFilterRestlet(context);
	ClientInfoRestlet clientInfoRestlet = new ClientInfoRestlet(context);

	IpAddressBlockingFilterRestlet ipAddressFilterRestlet = new IpAddressBlockingFilterRestlet(context);
	ipAddressFilterRestlet.setNext(clientInfoRestlet);
	ipAddressFilterRestlet.getBlacklistedIps().add("127.0.0.1");
	
	Router router = new Router(context);
	router.attach("http://localhost/", clientInfoRestlet);
	router.attach("http://localhost/admin", adminFilterRestlet);
	router.attach("http://localhost/info", ipAddressFilterRestlet);
	
	return router;
  }

}
