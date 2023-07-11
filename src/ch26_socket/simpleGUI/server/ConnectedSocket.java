package ch26_socket.simpleGUI.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch26_socket.simpleGUI.client.dto.SendMessage;
import ch26_socket.simpleGUI.server.dto.RequestBodyDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {
	
	private final Socket socket;	//매개변수를 socket으로 받는 생성자
	private String username;
	
	@Override
	public void run() {
		while(true) {
			try {
				BufferedReader bufferedReader = 
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				
				
				requestController(requestBody);
				
//				1번 for문으로 출력
//				SimpleGUIserver.connectedSocketList.forEach(connectedSocket -> {
//					try {	//for문을 돌리면서 해당 리스트에 있는 객체를 하나씩 꺼내어 Output
//						PrintWriter printWriter = 
//								new PrintWriter(connectedSocket.socket.getOutputStream(), true);
//						printWriter.println(requestBody);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				});
//				2번 for문
//				for(ConnectedSocket connectedSocket : SimpleGUIserver.connectedSocketList) {
//				}
//				3번 for문
//				for(int i = 0; i < SimpleGUIserver.connectedSocketList.size(); i++) {
//				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void requestController(String requestBody) {
		Gson gson = new Gson();
		
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();

//		익명클래스
//		TypeToken<RequestBodyDto<SendMessage>> token = new TypeToken<RequestBodyDto<SendMessage>>() {};
//		RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, token.getType());
		
		switch (resource) {
			//유저네임을 가져오는 스위치 케이스문
			case "Join":
				username = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
				
				//클라이언트에게 두 개의 데이터를 모든 클라이언트에게 보내는 것
				SimpleGUIserver.connectedSocketList.forEach(connectedSocket -> {
					//새로운 리스트를 생성해 connectedSocketList에 흩어져 있는 username을 한곳으로 모아준다.
					List<String> usernameList = new ArrayList<>();
					
					SimpleGUIserver.connectedSocketList.forEach(con -> {
						usernameList.add(con.username);
					});
					
					// list에 있는 이름을 업데이트
					RequestBodyDto<List<String>> updateUserListDto = new RequestBodyDto<List<String>> ("updateUserList", usernameList);
					RequestBodyDto<String> joinMessageDto = new RequestBodyDto<String>("showMessage", username + "님이 들어왔습니다.");
					
					ServerSender.getInstance().send(connectedSocket.socket, updateUserListDto);
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ServerSender.getInstance().send(connectedSocket.socket, joinMessageDto);
					
				});
				
				break;
	
			case "sendMessage":

				TypeToken<RequestBodyDto<SendMessage>> typeToken = new TypeToken<>() {};
				
//제네릭타입안에 비어있을 경우는 .class를 사용해도 되지만 제네릭타입안에 값이 들어가는 경우는 getType으로 명확하게 명시해줘야한다.
				RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, typeToken.getType());
				SendMessage sendMessage = requestBodyDto.getBody();	//Map으로 꺼낼 수 있던 것을 sendMessage 객체로 꺼낼 수 있음
				System.out.println(sendMessage);
				
				//forEach를 돌려 해당 메시지를 전부에게 보여줘야함
				SimpleGUIserver.connectedSocketList.forEach(connectedSocket -> {
					RequestBodyDto<String> dto = 
							new RequestBodyDto<String>("showMessage", sendMessage.getFromUsername() + ": " + sendMessage.getMessageBody());
					
					ServerSender.getInstance().send(connectedSocket.socket, dto);
				});
						
				break;
		}
	}
}