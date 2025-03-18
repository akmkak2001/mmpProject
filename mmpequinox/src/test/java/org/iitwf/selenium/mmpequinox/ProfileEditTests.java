package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.ProfileEditPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class ProfileEditTests extends FrameworkLibrary{
	
	ProfileEditPage profile;
	private ExtentTest extentTest;
	
	@Test
	public void editAndUpdateProfileVlaues() throws IOException
	{
		
		extentTest = extentReports.createTest("######## Edit Profile Test ########");

	    String patientUrl = prop.getProperty("patient_url");
	    launchBrowser(patientUrl);
	    
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Profile");
		extentTest.info("Navigating to Profile Page");
		extentTest.info("Screen Print Before Editing");
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("ProfilePageWithoutAnyEdits");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");
		
		profile = new ProfileEditPage(driver);
		profile.editAndUpdateProfile();
		
		extentTest.info("Screen Print After Editing");
		ScreenshotUtil screenshotUtil1 = new ScreenshotUtil(driver);	
		String screenshotPath1 = screenshotUtil1.captureScreenshot("ProfilePageWithEdits");
		extentTest.addScreenCaptureFromPath(screenshotPath1,"Edit_Profile_Page");

	}

}
