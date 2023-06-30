package ch11_문자열;

public class String05 {
	public static void main(String[] args) {
		String name = " ";
		
		boolean flag = name.isBlank();
		System.out.println(flag);
		
		boolean flag2 = name.isEmpty();
		System.out.println(flag2);
	}
}
