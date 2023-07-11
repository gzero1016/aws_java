package ch26_socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	//final이 붙은 변수만 매개변수 자동 생성
public class ConnectedSocket extends Thread {
	
	private final Socket socket;	//final을 붙이면 무조건 초기화가 일어나야함
	
	@Override
	public void run() {
		BufferedReader bufferedReader = null;
//		BufferedReader : 해당 데이터가 넘어오자마자 바로 처리하기 위해 사용
		try {
			while (true) {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				System.out.println("입력데이터: " +requestBody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}