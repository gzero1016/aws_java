package ch14_싱글톤;


public class FactoryMain {

	public static void main(String[] args) {
		Samsung samsung = Samsung.getInstance();
		System.out.println(samsung.getCompanyName());
		
		SamsungFactory factory1 = new SamsungFactory();
		SamsungFactory factory2 = new SamsungFactory();
		
		factory1.produce("갤럭시s").showInfo();
		factory2.produce("갤럭시s2").showInfo();
		factory1.produce("갤럭시s3").showInfo();
		
		
		
	}
	
}
