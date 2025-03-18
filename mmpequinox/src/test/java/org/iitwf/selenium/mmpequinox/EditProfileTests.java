package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.EditProfilePage;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class EditProfileTests extends FrameworkLibrary {

	private ExtentTest extentTest;

	@Test
	public void validateEditFuncAllFields() throws IOException
	{
		extentTest = extentReports.createTest("############Validate Edit Profile Tests########");

	    launchBrowser(prop.getProperty("patient_url"));
	    
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Profile");
		extentTest.info("Navigating to Profile Page");
		
		
		
		
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step1");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");


		EditProfilePage editProjObj = new EditProfilePage(driver);
		String actualFName = editProjObj.editAllFields();
		String expectedFName=prop.getProperty("patient_username");
		extentTest.info("Expected FName::" + expectedFName);
		extentTest.info("Actual FName::"+  actualFName );
		
		
		Assert.assertEquals(actualFName, expectedFName);

	}

}

