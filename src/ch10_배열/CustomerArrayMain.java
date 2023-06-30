package ch10_배열;

public class CustomerArrayMain {
	
	public static void main(String[] args) {
		Customer[] customers = new Customer[10];
		
		customers[0] = new Customer("김준일", "Gold");
		customers[1] = new Customer("이동헌", "Silver");
		customers[2] = new Customer("문근해", "Vip");
		
		for(int i = 0; i < customers.length; i++) {
			if(customers[i] == null) {
				continue;
			}
			
			customers[i].showInfo();
			
		}
		
//		customer1.showInfo();
//		customer2.showInfo();
//		customer3.showInfo();
		
	}
	
}

