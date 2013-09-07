package com.smc.restlet.mailserver.server;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class MailServerApplicationRoutersAndRestlets extends Application {

  //  public static void main(String[] args) throws Exception {
  //	Server mailServer = new Server(Protocol.HTTP, 8111);
  //	mailServer.setNext(new MailServerApplicationRoutersAndRestlets());
  //
  //	mailServer.setName("My Mail Server");
  //	mailServer.setAuthor("smc");
  //
  //	mailServer.start();
  //  }

  @Override
  public Restlet createInboundRoot() {
	Context context = getContext();

	AdminFilterRestlet adminFilterRestlet = new AdminFilterRestlet(context);
	ClientInfoRestlet clientInfoRestlet = new ClientInfoRestlet(context);

	IpAddressBlockingFilterRestlet ipAddressFilterRestlet = new IpAddressBlockingFilterRestlet(
	    context);
	ipAddressFilterRestlet.setNext(clientInfoRestlet);
	ipAddressFilterRestlet.getBlacklistedIps().add("127.0.0.1");

	// return ipAddressFilterRestlet;
	// return adminFilterRestlet;
	// return clientInfoRestlet;

	 Router router = new Router(context);
	 router.setDefaultMatchingMode(Template.MODE_EQUALS);
	 router.attach("http://localhost/", clientInfoRestlet);
	 router.attach("http://localhost/admin", adminFilterRestlet);
	 router.attach("http://localhost/client", clientInfoRestlet);
	 router.attach("http://localhost/filter", ipAddressFilterRestlet);
	
	 return router;
  }

}
