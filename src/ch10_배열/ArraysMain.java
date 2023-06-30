package ch10_배열;

import java.util.Arrays;

public class ArraysMain {
	
	public static void main(String[] args) {
		int[] numbers = new int[] { 10, 4, 5, 2, 8, 7, 1 };
		
		
		for(int i = 0; i < numbers.length; i++) {
			if(i == 0) {
				System.out.print("[ ");				
			}
			
			System.out.print(numbers[i]);
			
			if(i == numbers.length - 1) {
				System.out.println(" ]");
			}else {
				System.out.print(", ");
			}
		}
		
		Arrays.sort(numbers);
		
		for(int i = 0; i < numbers.length; i++) {
			if(i == 0) {
				System.out.print("[ ");				
			}
			
			System.out.print(numbers[i]);
			
			if(i == numbers.length - 1) {
				System.out.println(" ]");
			}else {
				System.out.print(", ");
			}
		}
	}
	
}


