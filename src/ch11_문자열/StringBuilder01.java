package ch11_문자열;

public class StringBuilder01 {
	
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		builder.append("이름: ");
		builder.append("김준일");
		builder.delete(builder.indexOf(":"), builder.indexOf(":") + 1);
		builder.insert(2, ">>");
		
		String str = builder.toString();
		System.out.println(str);
		
		String name = "이름: 김준일";
		
		System.out.println(
				name.substring(0, name.indexOf(":"))
				.concat(name.substring(name.indexOf(":") + 1)));
		
		System.out.println(
				name.substring(0, 2)
				.concat(name.substring(3)));
		
		
	}
	
}










