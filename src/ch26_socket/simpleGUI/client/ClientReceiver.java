package ch26_socket.simpleGUI.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

import ch26_socket.simpleGUI.server.dto.RequestBodyDto;

public class ClientReceiver extends Thread {
	
	private Gson gson;
	
	@Override
	public void run() {
		gson = new Gson();
		
		SimpleGUIClient simpleGUIClient = SimpleGUIClient.getInstance();
		//SimpleGUIClient를 싱글톤으로 만들어야 사용가능하다.
		
		while(true) {
			try {
				BufferedReader bufferedReader = 
						new BufferedReader(new InputStreamReader
								(SimpleGUIClient.getInstance().getSocket().getInputStream()));
				String requestBody = bufferedReader.readLine();
				
				requestController(requestBody);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void requestController(String requestBody) {

		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();	//해당 주소에있는 값 가져오기
		
		switch(resource) {
			case "updateRoomList":
				updateRoomList(requestBody);
				break;
				
				
			case "showMessage":
				showMessage(requestBody);
				break;
				
				
			case "updateUserList":
				updateUserList(requestBody);
				break;
		}
	}
	
	private void updateRoomList(String requestBody) {
		List<String> roomList = (List<String>) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		SimpleGUIClient.getInstance().getRoomListModel().clear();
		SimpleGUIClient.getInstance().getRoomListModel().addAll(roomList);
	}

	private void showMessage(String requestBody) {
		String messageContent = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		SimpleGUIClient.getInstance().getChattingTextArea().append(messageContent + "\n");
	}
	
	private void updateUserList(String requestBody) {
		List<String> usernameList = (List<String>) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		SimpleGUIClient.getInstance().getUserListModel().clear();	//리스트를 비워줌
		SimpleGUIClient.getInstance().getUserListModel().addAll(usernameList);	//리스트안에 있는 전체를 추가
		//addElement: 리스트 맨 마지막에 넣어줌
	}
}