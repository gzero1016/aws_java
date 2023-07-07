package ch26_socket.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServerApplication {
	
	public static ServerSocket serverSocket;
	public static int port;
	
	public static void main(String[] args) {	//main문은 static
		
		Thread connectionThread = null;	//Thread 객체 선언
		
		System.out.println("[ 서버프로그램 실행 ]");
		
		while(true) {	//main문 반복
			Scanner scanner = new Scanner(System.in);	//반복 돌때마다 스캐너 생성
			
			int selectedMenu = 0;	//메뉴선택에 대한 변수
			System.out.println("1. 서버켜기");
			System.out.println("2. 서버끄기");
			System.out.print("선택: ");
			
			try {
				selectedMenu = scanner.nextInt();	//선택받는 것
			} catch (InputMismatchException e) {	//문자열을 입력받았을때 예외처리
				System.out.println("숫자만 입력 가능합니다.");
				continue;	//다시 반복 맨 처음으로 돌아감
			}
			
			switch(selectedMenu) {
				case 1:
					if (serverSocket != null) {		//소켓이 비어있지 않으면
						System.out.println("이미 서버가 실행중입니다.");
						break;	//스위치 break를 만나면 다시 while 위로 올라감
					}
					System.out.print("서버의 포트번호를 입력하세요: ");	//break가 안걸렸을 시 포트번호 입력 받음
					
					try {
						port = scanner.nextInt();	//포트번호 입력 받음
					} catch (InputMismatchException e) {	//문자열을 입력받았을때 예외처리
						System.out.println("숫자만 입력 가능합니다.");
						continue;
					}
					
					connectionThread = new Thread(() -> {	//람다로 Thread 생성
						try {
							serverSocket = new ServerSocket(port);	//다른사람이 해당 포트를 사용하고있으면 오류가 나니 예외처리 해줘야함
							
							while(!Thread.interrupted()) {	//interrupt가 True인 상태를 False로 변경되면서 반복이 멈추고 Thread 자연스럽게 소멸됨
								Socket socket = serverSocket.accept();	//Scanner 넥스트랑 비슷 접속기다림
								System.out.println("접속!!");
								System.out.println(socket.getInetAddress().getHostAddress());
							}
							
						}catch (BindException e) {	//같은 포트가 두번 이상 실행될때 예외처리
							System.out.println("이미 사용중인 포트번호입니다.");
						} catch (SocketException e) {	//소켓 객체 소멸 될시 예외처리
							System.out.println("서버의 연결이 종료되었습니다.");
						}  catch (IOException e) {
							e.printStackTrace();
						}
					}, "connectionThread");
					
					connectionThread.start();	//Thread 실행 (호출안하면 실행안됨)
					
					break;
					
				case 2:
					if(serverSocket == null) {
						System.out.println("서버가 실행중이지 않습니다.");
						break;
					}
					try {
						serverSocket.close();	//소켓 객체 소멸 (서버를 죽이는것)					
					} catch (IOException e) {}
					
					connectionThread.interrupt();	//Thread돌고있는 상황에서 interrupt가 끼어드는 것
					
					try {
						connectionThread.join();	//Thread가 종료될때 까지 join을 사용해 기다림
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("프로그램 종료");	//위에서 조인을 걸어서 Thread 종료 후 main 종료
					return;	//함수가 빠져나가면서 프로그램종료
					
				default:	// 1,2번을 제외한 다른 정수가 들어왔을 시 처리하는 부분 명령 실행 후 while 맨 위로 올라감
					System.out.println("다시 선택하세요.");
			}
		}	
	}
}