package org.iitwf.selenium.mmpequinox;

import org.iitwf.mmp.pages.patientmodule.RegistrationPatientPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class RegistrationPatientTests extends FrameworkLibrary {
	
	 
	RegistrationPatientPage RPPage;
	private ExtentTest extentTest;
	
		
	@Test()
	public void validateRegistration() throws Exception
	{  
		extentTest = extentReports.createTest("######## Patient Registration Test ########");
		
		launchBrowser(prop.getProperty("patient_url"));
		RPPage = new RegistrationPatientPage(driver);
		extentTest.info("Clicked on Register Button");
		RPPage.clickRegisterButton();
		RPPage.fillData();
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Registration");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Registering Patient");
		String actual = RPPage.clickOnSaveButton();
		String expected ="Thank you for registering with MMP. ";
		Assert.assertEquals(actual, expected);
		extentTest.info("Patient Registration Complete");
	}
	
	
	
}