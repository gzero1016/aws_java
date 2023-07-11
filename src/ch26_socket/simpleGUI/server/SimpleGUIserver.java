package ch26_socket.simpleGUI.server;

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
				ConnectedSocket connectedSocket = new ConnectedSocket(socket);	//Thread 객체
				connectedSocketList.add(connectedSocket);	//생성된 Thread 객체 리스트에 넣기
				connectedSocket.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
