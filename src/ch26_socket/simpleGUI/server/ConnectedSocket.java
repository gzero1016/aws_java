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
import ch26_socket.simpleGUI.server.entity.Room;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {
	
	private final Socket socket;	//매개변수를 socket으로 받는 생성자
	private Gson gson;
	
	private String username;
	
	@Override
	public void run() {
		gson = new Gson();
		
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
		
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();

//		익명클래스
//		TypeToken<RequestBodyDto<SendMessage>> token = new TypeToken<RequestBodyDto<SendMessage>>() {};
//		RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, token.getType());
		
		switch (resource) {
			case "connection":	//connectedSocket이 만들어 질 때마다 유저네임 구현
				connection(requestBody); //메소드 호출
				break;
		
			case "createRoom":
				creatRoom(requestBody);	//메소드 호출
				break;
		
			case "join":
				join(requestBody); //메소드 호출
				break;
	
			case "sendMessage":
				sendMessage(requestBody); //메소드 호출
				break;
		}
	}
	
	//변수명 중복사용을 위해 메소드로 빼버림 (switch, case 문 안에는 위에서 먼저 선언한 변수명을 중복사용 할 수 없음)
	private void connection(String requestBody) {
		username = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		
		List<String> roomNameList = new ArrayList<>();	//roomNameList 생성
		
		SimpleGUIserver.roomList.forEach(room -> {	//roomNameList에 roomName을 하나씩 빼서 새롭게 정의
			roomNameList.add(room.getRoomName());
		});
		
		RequestBodyDto<List<String>> updateRoomListRequestBodyDto = 
				new RequestBodyDto<List<String>>("updateRoomList", roomNameList);
		
		ServerSender.getInstance().send(socket, updateRoomListRequestBodyDto);	//자기 자신에게만 방리스트 전달
	}
	
	private void creatRoom(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();	//Body가 roomName을 받아옴
		
		Room newRoom = Room.builder()
			.roomName(roomName)	//받아온 roomName
			.owner(username)
			.userList(new ArrayList<ConnectedSocket>())
			.build();
		
		SimpleGUIserver.roomList.add(newRoom);	//server클래스 static리스트에 위에서 새로 생성된 리스트를 담겠다.
		
		List<String> roomNameList = new ArrayList<>();	//roomNameList 생성
		
		SimpleGUIserver.roomList.forEach(room -> {	//roomNameList에 roomName을 하나씩 빼서 새롭게 정의
			roomNameList.add(room.getRoomName());
		});
		
		RequestBodyDto<List<String>> updateRoomListRequestBodyDto = 
				new RequestBodyDto<List<String>>("updateRoomList", roomNameList);
		
		SimpleGUIserver.connectedSocketList.forEach(con -> {	//접속한 사용자 모두에서 roomNameList를 뿌려준다.
			ServerSender.getInstance().send(con.socket, updateRoomListRequestBodyDto);
		});
	}
	
	private void join(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		
		//내가들어간 룸과 룸리스트의 룸이름이 같으면 내 자신을 해당 룸 리스트에 추가
		SimpleGUIserver.roomList.forEach(room -> {
			if(room.getRoomName().equals(roomName)) {
				room.getUserList().add(this);	//나 자신을 리스트에 추가
				
				//새로운 리스트를 생성해 connectedSocketList에 흩어져 있는 username을 한곳으로 모아준다.
				List<String> usernameList = new ArrayList<>();
				
				//룸 안에 있는 사람들과 소통 SimpleGUIserver.connectedSocketList <- 요건 전체
				room.getUserList().forEach(con -> {
					usernameList.add(con.username);
				});
				
				//룸 안에 있는 접속한 사람들의 반복을 돌려 유저리스트 업데이트, 접속알림 띄움
				room.getUserList().forEach(ConnectedSocket -> {
					// list에 있는 이름을 업데이트
					RequestBodyDto<List<String>> updateUserListDto = new RequestBodyDto<List<String>> ("updateUserList", usernameList);
					RequestBodyDto<String> joinMessageDto = new RequestBodyDto<String>("showMessage", username + "님이 들어왔습니다.");
					
					ServerSender.getInstance().send(ConnectedSocket.socket, updateUserListDto);
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ServerSender.getInstance().send(ConnectedSocket.socket, joinMessageDto);
				});
			}
		});
		
		//클라이언트에게 두 개의 데이터를 모든 클라이언트에게 보내는 것
		SimpleGUIserver.connectedSocketList.forEach(connectedSocket -> {
		
	});
}
	
	private void sendMessage(String requestBody) {
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
	}
	
}