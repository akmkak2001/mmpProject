package org.iitwf.selenium.mmpequinox;

import java.util.HashMap;
import java.util.List;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class SanitySuiteValidateText extends FrameworkLibrary {
	
	private ExtentTest extentTest;

 
	@Test
	public void performHealthCheckTests()
	{	
		extentTest = extentReports.createTest("######## SanitySuiteValidateText Test ########");
		
		MMPUtility mmpUtil = new MMPUtility(driver);
		launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		
		HashMap<String,String> actualMenuTitleText = fetchMenuTitleText();
		
		extentTest.info("Healtcheck Completed");
 
	}
	public   HashMap<String,String> fetchMenuTitleText()
	{
		List<WebElement> actualList = driver.findElements(By.xpath("//div[@class='sidebar-holder']/ul/li/a/span"));
		HashMap<String,String> actualMenuTitleText = new HashMap<String,String>();
		for(int i = 0;i<actualList.size()-1;i++)
		{
			focusOnWebElement(actualList.get(i));
			actualList.get(i).click();
			actualList = driver.findElements(By.xpath("//div[@class='sidebar-holder']/ul/li/a/span"));
			actualMenuTitleText.put(actualList.get(i).getText(),driver.findElement(By.xpath("//div[@class='panel-heading']")).getText());
		}
		System.out.println("Actual Menu Items Text:::"+ actualMenuTitleText);
		return actualMenuTitleText;
	}
	public   void focusOnWebElement(WebElement ele)
	{
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
	}
	 
}











 