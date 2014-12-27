package org.raydelto.redirector.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PortRedirector extends Thread{
	private int localPort;
	private int redirectPort;
	private ServerSocket server;
	private Socket client;
	private Socket redirector;
	private PortListener hostListener;
	private PortListener redirectorListener;
	private boolean disabled = false;	
	
	
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Message while stop the redirector:" + e.getMessage());
		}
	}

	public PortRedirector(int localPort, int redirectPort) {
		this.localPort = localPort;
		this.redirectPort = redirectPort;
	}
	
	public void run(){
		System.out.println("Starting the redirection from " + localPort + "  to the port: " + redirectPort);
		try {
			server = new ServerSocket(redirectPort);			
		} catch (IOException e) {
			System.err.println("Could not start local server at port " + redirectPort);			
			e.printStackTrace();
			System.exit(0);
		}

		while(!disabled){
			try{
				if((client = server.accept())!= null){
					System.out.println("Connection received");
					try {
						System.out.println("Connecting to localhost at port " + localPort);
						redirector = new Socket("localhost", localPort);
					} catch (IOException e) {
						System.err.println("Could not connect to the redirected port, exiting the program");			
						e.printStackTrace();
						System.exit(0);
					}					
					
					hostListener = new PortListener(client.getInputStream(), redirector.getOutputStream(), client);
					redirectorListener = new PortListener(redirector.getInputStream(), client.getOutputStream(), redirector);
					hostListener.setFriendListener(redirectorListener);
					redirectorListener.setFriendListener(hostListener);
					hostListener.start();
					redirectorListener.start();
					
				}
			}catch(Exception e){
				System.out.println("Exiting redirector main loop: " + e.getMessage());
			}					
		}	
		System.out.println("Stoping the redirection from " + localPort + "  to the port: " + redirectPort);		
	}	
	
}
