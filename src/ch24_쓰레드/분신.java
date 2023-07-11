package ch24_쓰레드;

public class 분신 extends Thread {
	
	private static int number;
	private int num;
	
	public 분신() {
		number += 1;
		num = number;
	}
	// 상속받은 부모클래스의 메소드를 상속받아 재정의하는것
	// 프로그램 실행 전에 변경해야함
	@Override	//start와 동시에 run 부분 실행
	public void run() {
		System.out.println(num + "분신하나 실행");
		while (true) {
			System.out.println(num + "분신 반복");
			try {
				Thread.sleep(3000);	//1000 에 1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}