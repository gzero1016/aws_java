package ch08_생성자;

public class Student {
	
	final String name;
	int age;
	String address;
	
//	Student() {
//		System.out.println("NoArgsConstructor");
//		System.out.println("학생 한명을 생성합니다.");
//	}
	
	Student(String name, int age, String address) {
		System.out.println("AllArgsConstructor");
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	Student(String name) {
		System.out.println("RequiredArgsConstructor");
		this.name = name;
	}
	
}





