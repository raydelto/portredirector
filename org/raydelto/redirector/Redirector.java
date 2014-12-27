package org.raydelto.redirector;

import org.raydelto.redirector.net.PortRedirector;

public class Redirector {
	public static void main(String[] args) {
		int localPort = 0;
		int destinationPort = 0;
		if(args.length < 2){
			System.out.println("USAGE:  argument1: source port  ,  argument2: destination port");
		}else{
			localPort = Integer.parseInt(args[0]);
			destinationPort = Integer.parseInt(args[1]);
			PortRedirector pr = new PortRedirector(localPort, destinationPort);
			System.out.println("About to start the redirector thread:");
			pr.start();
			System.out.println("After the redirector thread start method invocation");
		}
	}
}
