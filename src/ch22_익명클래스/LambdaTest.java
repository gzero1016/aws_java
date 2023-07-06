package ch22_익명클래스;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {
	
	public static void main(String[] args) {
		GrantedAuthorities authorities2 = new GrantedAuthorities() {
			
			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		};
		
		GrantedAuthorities authorities = () -> "ROLE_USER";
		
			
		
		System.out.println(authorities.getAuthority());
		
		Integer[] test = {1,2,3,4,5,6,7,8,9,10};
		
		Arrays.asList(test).forEach(num -> System.out.println(num));
		
	}
	
}
