package ch10_배열;

public class Customer {
	private String name;
	private String rating;
	
	// 전체 생성자
	public Customer(String name, String rating) {
		this.name = name;
		this.rating = rating;
	}
	
	public void showInfo() {
		System.out.println("고객명: " + name);
		System.out.println("고객등급: " + rating);
		System.out.println();
	}
}







