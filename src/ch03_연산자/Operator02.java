package ch03_연산자;

import java.io.File;

/*
 * 논리연산자
 * 1. && -> and(그리고) - 곱
 * true && false => false
 * 2. || -> or(또는)	- 합
 * true || false => true
 * 3. !  -> not(부정)	- 반전
 * !(true || false) => false
 * !true => false
 */

public class Operator02 {
	public static void main(String[] args) {
		int year = 1999;
		System.out.println(year % 4 == 0);
		System.out.println(year % 100 != 0);
		System.out.println(year % 400 == 0);
		System.out.println(year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 1 : 0);
		
		int result = 10 + 1;
		System.out.println(result);
		
		boolean result2 = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
		System.out.println(result2);
		
		String result3 = year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? "1" : "0";
		System.out.println(result3);

	}
}
