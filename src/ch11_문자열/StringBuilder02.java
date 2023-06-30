package ch11_문자열;

public class StringBuilder02 {
	public static void main(String[] args) {
		// 권한
		String[] roles = { "ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN" };
		
		String strRoles = "";
		
		for(int i = 0; i < roles.length; i++) {
			strRoles += roles[i];
			if(i != roles.length - 1) {
				strRoles += ", ";
			}
		}
		
		System.out.println(strRoles);
		
		StringBuilder strRolesBuilder = new StringBuilder();
		
		for(int i = 0; i < roles.length; i++) {
			strRolesBuilder.append(roles[i] + ", ");
		}
		strRolesBuilder.delete(strRolesBuilder.lastIndexOf(", "), strRolesBuilder.length());
		
		System.out.println(strRolesBuilder.toString());
	}
}









