package ch24_쓰레드;

public class TheadMain {
	
	public static void main(String[] args) {
		
		Thread thread1 = new Thread(() -> {
			System.out.println("스레드 이름: " + Thread.currentThread().getName());
			
			for(int i = 0; i < 50; i++) {
				System.out.print(Thread.currentThread().getName() + ": ");
				System.out.println(i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, "1번 스레드");
		
		Thread thread2 = new Thread(() -> {
			System.out.println("스레드 이름: " + Thread.currentThread().getName());
			
			for(int i = 0; i < 50; i++) {
				System.out.print(Thread.currentThread().getName() + ": ");
				System.out.println(i);
			}
			
		}, "2번 스레드");
		
		Thread thread3 = new Thread(new ThreadTest(), "3번 스레드");
		
		Thread thread4 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("스레드 이름: " + Thread.currentThread().getName());
				System.out.println("4번 스레드 실행");
			}
		}, "4번 스레드");
		
		thread1.start();
//		try {
//			thread1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		thread2.start();
//		thread3.start();
//		thread4.start();
		
		
	}
	
}
