package ch20_컬렉션;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAndMapMain {
	public static void main(String[] args) {
		List<TestUser> testUsers = new ArrayList<>();
		testUsers.add(TestUser.builder().username("aaa").password("1234").build());
		testUsers.add(TestUser.builder().username("bbb").password("1234").build());
		testUsers.add(TestUser.builder().username("ccc").password("1234").build());
		testUsers.add(TestUser.builder().username("ddd").password("1234").build());
		testUsers.add(TestUser.builder().username("eee").password("1234").build());
		
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("testUserList", testUsers);
		responseData.put("statusCode", "OK");
		
		for(TestUser testUser : (List<TestUser>) responseData.get("testUserList")) {
			System.out.println(testUser);
		}
	}
}












