package org.iitwf.mmp.pages.patientmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MMPUtility {

	WebDriver driver;
	public MMPUtility(WebDriver driver)
	{
		this.driver= driver;
		
	}
	public void login(String username,String password)
	{

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		driver.getTitle();
	}
	
	public void adminLogin(String username,String password)
	{

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();
		
	}
}
