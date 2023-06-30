package ch14_싱글톤;

import java.time.LocalDate;

public class Samsung {
	private static Samsung instance;
	
	private String companyName;
	private int autoIncrementSerialNumber = LocalDate.now().getYear() * 10000;
	
	private Samsung() {
		companyName = Samsung.class.getSimpleName().toUpperCase();
	}
	
	public static Samsung getInstance() {
		if(instance == null) {
			instance = new Samsung();
		}
		return instance;
	}
	
	public int getAutoIncrementSerialNumber() {
		return autoIncrementSerialNumber;
	}
	public void setAutoIncrementSerialNumber(int autoIncrementSerialNumber) {
		this.autoIncrementSerialNumber = autoIncrementSerialNumber;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	
}
