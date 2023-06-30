package ch11_문자열;

import java.nio.charset.StandardCharsets;

public class String01 {
	
	public static void main(String[] args) {
		String str = new String("코리아 아이티 아카데미".getBytes(StandardCharsets.UTF_8));
		System.out.println(str == "코리아 아이티 아카데미");
	}
	
}
