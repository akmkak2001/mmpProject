package org.iitwf.mmp.pages.patientmodule;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	protected WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		if (!driver.getTitle().equals("home")) {
			throw new IllegalStateException("This is not Home Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	public   void navigatetoAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();
	}
	public  HashMap<String, String>  fetchAppointmentDetails()
	{

		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("doctor",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText().trim() );
		actualHMap.put("date",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText().trim()  );
		actualHMap.put("time",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText().trim() );
		actualHMap.put("sym", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText().trim());
		return actualHMap;
	}
}
