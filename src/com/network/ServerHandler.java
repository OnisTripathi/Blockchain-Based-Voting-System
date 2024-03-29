/**
 * 
 */
package com.network;

import java.io.IOException;
import java.net.Socket;


public class ServerHandler extends Thread{
	/* the socket to receive messages from */
	private Socket _socket = null;
	/* used for callback */
	public ServerManager _svrMgr = null;
	
	public ServerHandler(ServerManager svrMgr, Socket socket) {
		_svrMgr = svrMgr;
		_socket = socket;
	}
	

	@Override
	public void run() {
		while (true) {
			try {
				_svrMgr.receiveMsg(_socket);
			} catch (IOException | ClassNotFoundException e) {
				_svrMgr.clientDisconnected(_socket);
				break;
			}
		}
		
	}
}

