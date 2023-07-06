package ch21_JSON;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Json01 {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("productCode", "P20230704");
		dataMap.put("productName", "아이폰15");
		
		System.out.println(dataMap);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("productCode", "P20230704");
		jsonObject.addProperty("productName", "아이폰15");
		
		System.out.println(jsonObject);
		
		System.out.println(gson.toJson(dataMap));
		Map<String, Object> jsonMap = gson.fromJson(jsonObject, Map.class);
		System.out.println(jsonMap.get("productCode"));
		
	}
	
}







