package ch03_연산자;

public class Operator03 {
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		int c = 5;
		int max = 0;
		
		max = a < b ? b : a;
		max = max < c ? c : max;
		
		System.out.println(max);
		
		System.out.println(a < b ? b : (a < c ? c : a));
	}
}
