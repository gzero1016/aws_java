package ch04_입력;

import java.util.Scanner;

public class Input01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("입력1: ");
		String input1 = scanner.nextLine();
		System.out.println("출력1: " + input1);
		
		System.out.print("입력2: ");
		String input2 = scanner.nextLine();
		System.out.println("출력2: " + input2);
		
		System.out.println("입력완료.");

		
	}
}
