package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.util.HashMap;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.ScheduleAppointmentPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ListenerUtility;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;


@Listeners(ListenerUtility.class)
public class ScheduleAppointmentTests extends FrameworkLibrary{


	private ExtentTest extentTest;
	 
	@Test
	public void validateBookAppointmentTests() throws IOException 
	{
        extentTest = extentReports.createTest("######## Schedule Appointment Test ########");
        
        launchBrowser(prop.getProperty("patient_url"));
        
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
 
		hPage.navigatetoAModule("Schedule Appointment");
        extentTest.info("Navigating to Schedule Appointment");
        
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("ScheduleAppointment_Page1");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_Schedule_Appointment_Page");

		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap= sPage.bookAppointment(60,"MMMM/d/YYYY","Cardiologist","Charlie");
        extentTest.info("Expected HMap: " + expectedHMap);

		
		HashMap<String,String> actualHMap = hPage.fetchAppointmentDetails();
		extentTest.info("actualHMap : " + actualHMap);
		
		screenshotUtil = new ScreenshotUtil(driver);	
        screenshotPath = screenshotUtil.captureScreenshot("ScheduleAppointment_Page2");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Schedule_Appointment_Completed");

		Assert.assertEquals(actualHMap, expectedHMap,"Booking is unsuccessful");		 
	}
}


