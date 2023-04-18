package com.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public abstract class NetworkManager implements Runnable{
	

	public void sendMsg(Socket socket, MessageStruct msg) 
			throws IOException {
		ObjectOutputStream out;
		
		out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(msg);
	}
	
	/*
	 * Try to receive a message from socket.
	 */
	public void receiveMsg(Socket socket) 
			throws ClassNotFoundException, IOException {
		ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
		Object inObj = inStream.readObject();
		
		if (inObj instanceof MessageStruct) {
			MessageStruct msg = (MessageStruct) inObj;
			msgHandler(msg, socket);
		}
		
	}
	
	/*
	 * Close socket
	 */
	public void close(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public abstract void msgHandler(MessageStruct msg, Socket src);
}
