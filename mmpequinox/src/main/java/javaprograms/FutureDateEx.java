package javaprograms;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FutureDateEx {
	
	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().getTimeInMillis()%1000000000);
	
		
		String ipAddress="A.B.C.D";
		String ipAddressArr[] = ipAddress.split("\\.");
		System.out.println(ipAddressArr[3]);
		
		
		generateFutureDate(5,"MMMM/DD/YYYY");
		generateFutureDate(20,"MMMM/dd/YYYY");
		generateFutureDate(0,"MMMM/dd/YYYY");
		String str = generateFutureDate(-10,"MMMM/dd/YYYY");
		String strArr[] = str.split("/");
		System.out.println(strArr[0]);
		
	}

	public static String generateFutureDate(int n,String format)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, n);
		System.out.println(cal.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		String formattedDate = sdf.format(cal.getTime());
		System.out.println("Formatted Date:::" + formattedDate);
		return formattedDate;
//		
//		sdf = new SimpleDateFormat("MMMM/dd/YYYY");
//		formattedDate = sdf.format(cal.getTime());
//		System.out.println("Formatted Date:::" + formattedDate);
	}
}
