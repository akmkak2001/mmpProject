package org.iitwf.mmp.pages.patientmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Messages {
	
	WebDriver driver;
	
	By contactReason = By.id("subject");
	By message = By.id("message");
	By sendBtn = By.xpath("//input[@value='Send']");
	
	
	public Messages(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String contactReason(String reason)
	{
		driver.findElement(contactReason).sendKeys(reason);
		return reason;
		
	}
	
	public String message(String msg)
	{
		driver.findElement(message).sendKeys(msg);
		return msg;
	}
	
			
	public void send()
	{
		driver.findElement(sendBtn).click();
	}
}
