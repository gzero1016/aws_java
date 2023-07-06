package ch20_컬렉션;

import java.util.HashSet;

public class HashSetMain {
	public static void main(String[] args) {
		HashSet<TestUser> testUsers = new HashSet<>();
		testUsers.add(TestUser.builder().username("aaa").password("1234").build());
		testUsers.add(TestUser.builder().username("bbb").password("1111").build());
		testUsers.add(TestUser.builder().username("ccc").password("2222").build());
		testUsers.add(TestUser.builder().username("ddd").password("3333").build());
		
		System.out.println(testUsers);
		
		TestUser value = null;
		
		for(TestUser testUser : testUsers) {
			if(testUser.getUsername().equals("ccc")) {
				System.out.println(testUser);
				value = testUser;
				break;
			}
		}
		
		System.out.println(value);
		
	}
}
