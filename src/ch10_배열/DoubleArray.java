package ch10_배열;

public class DoubleArray {
	public static void main(String[] args) {
		String[][] students = new String[2][2];
		
		students[0][0] = "김준일";
		students[0][1] = "김해원";
		students[1][0] = "부산";
		students[1][1] = "서울";
		
		for(int i = 0; i < students.length; i++) {
			for(int j = 0; j < students[i].length; j++) {
				System.out.println(students[i][j]); 
			}
		}
	}
}
