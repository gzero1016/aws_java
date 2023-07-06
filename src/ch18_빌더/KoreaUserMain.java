package ch18_빌더;

public class KoreaUserMain {
	
	public static void main(String[] args) {
		KoreaUser koreaUser = KoreaUser.builder().userId(1).build();
		KoreaUser koreaUser2 = new KoreaUser(1, null, null, null, null);
		KoreaUser koreaUser3 = new KoreaUser();
		koreaUser3.setUserId(1);
		
	}
	
}
