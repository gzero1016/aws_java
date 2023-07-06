package ch20_컬렉션;

public class CustomArrayList {
	
	private String[] array;
	
	public CustomArrayList() {
		array = new String[0];
	}
	
	public void add(String str) {
		String[] newArray = new String[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		newArray[array.length] = str;
		array = newArray;
	}
	
	public void add(int index, String str) {
		String[] newArray = new String[array.length + 1];
		for(int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		
		newArray[index] = str;
		
		for(int i = index; i < array.length; i++) {
			newArray[i + 1] = array[i];
		}
		
		array = newArray;
	}
	
	public String remove() {
		String value = null;
		String[] newArray = new String[array.length - 1];
		
		value = array[array.length - 1];
		
		for(int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		
		return value;
	}
	
	public String remove(int index) {
		String value = null;
		String[] newArray = new String[array.length - 1];
		
		value = array[index];
//		for(int i = 0; i < array.length; i++) {
//			int tempIndex = i - 1;
//			if(i == index) {
//				continue;
//			}
//			if(i < index) {
//				tempIndex = i;
//			}
//			newArray[tempIndex] = array[i];
//		}
		for(int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		for(int i = index; i < array.length - 1; i++) {
			newArray[i] = array[i + 1];
		}
		array = newArray;
		
		return value;
	}
	
	public void set(int index, String str) {
		array[index] = str;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		for(String str : array) {
			builder.append(str + ", ");
		}
//		for(int i = 0; i < array.length; i++) {
//			builder.append(array[i] + ", ");
//		}
		builder.delete(builder.lastIndexOf(","), builder.length());
		builder.append("]");
		
		return builder.toString();
	}
}










