package javaprograms;

import java.util.ArrayList;
import java.util.List;

public class ListEx {
	
	public static void main(String[] args) {
		
		
		List<String> exList = new ArrayList<String>();
		exList.add("HOME");
		exList.add("Schedule Appointment");
	 
		List<String> acList = new ArrayList<String>();
		acList.add("HOME");
		acList.add("Schedule Appointment");
		
		System.out.println(acList.contains(exList.get(0)));
				 
	}

}
