package javaprograms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NullPointerEx {
	
	public static void main(String[] args) {
		
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		
		String filePath = System.getProperty("user.dir")+"/screenshots/"+"Test1"+"_"+timestamp+".png";
		
		System.out.println(filePath);
		
		 
	}

}
