package ch22_익명클래스;

public class AuthorityMain {
	
	public static void main(String[] args) {
		GrantedAuthorities authorities = new GrantedAuthorities() {
			
			@Override
			public String getAuthority() {
				System.out.println("권한출력");
				return "ROLE_USER";
			}
		};
		
		System.out.println(authorities.getAuthority());
		
		A a = new A();
		System.out.println(a.getAuthority());
	}
	
}

class A implements GrantedAuthorities {
	
	@Override
	public String getAuthority() {
		System.out.println("두번째 권한 출력");
		return "ROLE_ADMIN";
	}
}




