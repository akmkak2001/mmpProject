package javaprograms;

import java.util.HashMap;

public class HashMapEx {
	
	public static void main(String[] args) {
		
		HashMap<String,Integer> ageHMap = new HashMap<String,Integer>();
		ageHMap.put("James", 63);
		ageHMap.put("John", 50);
		
		System.out.println(ageHMap.get("James"));
		System.out.println(ageHMap);
		
	}

}
