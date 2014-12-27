package org.raydelto.redirector.net;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PortListener extends Thread {
	private InputStream reader;
	private OutputStream writer;
	private Socket socket;
	private boolean destroyed = false;
	private PortListener friendListener;
	
	public PortListener getFriendListener() {
		return friendListener;
	}

	public void setFriendListener(PortListener friendListener) {
		this.friendListener = friendListener;
	}

	public PortListener(InputStream reader, OutputStream writer, Socket socket) {		
		super();
		System.out.println("Creating port listener");
		this.reader = reader;
		this.writer = writer;
		this.socket = socket;
	}
	
	public void destroy(){
		if(!destroyed){
			try {
				reader.close();
				writer.close();
				socket.close();
				destroyed = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error while finalizing connection");				
				e.printStackTrace();
			}			
		}		
	}
	
	public void run(){
		int read = 0;
		int nothingToRead = 0;
		while(!destroyed){
			try{
				if( (read = reader.read()) != -1){
					writer.write(read);					
					//System.out.print((char) read);
				}else{
					nothingToRead++;
					if (nothingToRead > 10){
						destroyed = true;
					}
				}
			}catch(Exception e){
				System.out.println("Connection ended:" + e.getMessage());				
				destroy();
				friendListener.destroy();				
				System.gc();
			}
		}
		System.out.println("exiting reading loop");
	}

}
