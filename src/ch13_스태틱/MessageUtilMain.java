package ch13_스태틱;


public class MessageUtilMain {
	
	private int a;
	
	public static void main(String[] args) {
		MessageUtilMain messageUtilMain = new MessageUtilMain();
		
		System.out.println(messageUtilMain.a);
		
//		Array01.main(null);
		
		MessageUtil messageUtil = new MessageUtil();
//		
//		messageUtil.sendMail();
//		messageUtil.sendFile();
		
		MessageUtil.sendFile();
		System.out.println();
		
		MessageUtil.data = "안녕";
		System.out.println(MessageUtil.data);
		
		System.out.println(messageUtil.data);
	}
	
}
