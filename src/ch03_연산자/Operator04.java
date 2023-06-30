package ch03_연산자;

public class Operator04 {
	public static void main(String[] args) {
		int score = 80;
		int a = 1 + 2 + 3 + 4 + 5;
		
		String result = score < 0 || score > 100 ? "X" 
				: score > 89 ? "A" 
				: score > 79 ? "B" 
				: score > 69 ? "C" 
				: score > 59 ? "D"
				: "F";
		
		System.out.println(result);
	}
}
