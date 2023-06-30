package ch06_반복;

import java.util.Scanner;

public class While01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int selectedNumber = 0;
		boolean flag = true;
		
		while(flag) {
			System.out.println("[메뉴]");
			System.out.println("1. 1번");
			System.out.println("2. 2번");
			System.out.println("3. 3번");
			System.out.println("4. 4번");
			System.out.println("5. 종료");
			System.out.print("메뉴번호: ");
			selectedNumber = scanner.nextInt();
			
			if(selectedNumber == 5) {
				flag = false;
			} else if(selectedNumber == 1) {
				boolean flag2 = true;
				char selectedMenu = 0;
				
				while(flag2) {
					System.out.println("[1번 메뉴 안쪽]");
					System.out.println("a. a메뉴");
					System.out.println("b. b메뉴");
					System.out.println("c. 뒤로가기");
					System.out.println("d. 종료");
					System.out.print("메뉴선택: ");
					selectedMenu = scanner.next().charAt(0);
					
					if(selectedMenu == 'c') {
						flag2 = false;
					}
				}
			}
		}
		
		System.out.println("프로그램 종료");
	}
}

