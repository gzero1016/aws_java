package ch26_socket.test.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleGUIserver {
	
	public static List<ConnectedSocket> connectedSocketList = new ArrayList<>();
	
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("[ 서버실행 ]");
			
			while(true) {
				Socket socket = serverSocket.accept();
				ConnectedSocket connectedSocket = new ConnectedSocket(socket);
				connectedSocketList.add(connectedSocket);
				connectedSocket.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
