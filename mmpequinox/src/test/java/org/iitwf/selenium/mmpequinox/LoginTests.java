package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.ExcelUtils;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class LoginTests extends FrameworkLibrary {
	
	private ExtentTest extentTest;

	
	@DataProvider(name="DP")
	public String[][] feedData() throws IOException
	{
		String inputData[][] = ExcelUtils.getCellData("mmpdata.xlsx");
		return inputData;
	}
	
	@Test(dataProvider="DP")
	public void testLogin(String username,String password) throws IOException
	{
		extentTest = extentReports.createTest("######## Login Test ########"); 
		
		MMPUtility mmpUtil = new MMPUtility(driver);
		launchBrowser(prop.getProperty("patient_url"));
		extentTest.info("logging With Valid Credentials");
		mmpUtil.login(username,password);
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Home Page");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Logged In - Home Page");
		String actualText = driver.findElement(By.xpath("//h3[normalize-space()='Patient Portal']")).getText();
		String expectedText = "Patient Portal";
		Assert.assertEquals(actualText, expectedText);
		 
	}

}
