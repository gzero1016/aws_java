package ch19_제네릭;

public class ResponseMain {
	
	public static void main(String[] args) {
		Response<String> response1 = new Response<String>(200, "회원가입 성공!!");
		
		SignupUser signupUser = SignupUser.builder()
				.username("aaa")
				.password("1234")
				.name("김준일")
				.email("aaa@gmail.com")
				.build();
		
		Response<SignupUser> response2 = new Response<SignupUser>(400, signupUser);
		
		AccountUser accountUser = AccountUser.builder().username("aaa").password("1234").build();
		
		Response<AccountUser> response3 = new Response<AccountUser>(200, accountUser);
//		response3 = response2;
		
		UpdateUser updateUser = UpdateUser.builder()
				.username("aaa")
				.password("1234")
				.address("부산")
				.phone("010-0000-0000")
				.build();
		
		Response<UpdateUser> response4 = new Response<UpdateUser>(300, updateUser);
		
		printResponse(response3);
		
	}
	
	public static Response<?> printResponse(Response<? super SignupUser> response) {
		System.out.println(response);
		return response;
	}
	
}












