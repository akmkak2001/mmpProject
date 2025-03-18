package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.time.Duration;

import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.Messages;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class MessageTests extends FrameworkLibrary{
	
	Messages msg;
	private ExtentTest extentTest;
	
	@Test
	public void sendMessage() throws IOException
	{
		extentTest = extentReports.createTest("######## Messages Test ########");
		
		launchBrowser(prop.getProperty("patient_url"));
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		extentTest.info("Clicked on Messages Tile");
		hPage.navigatetoAModule("Messages");
		msg = new Messages(driver);
		//extentTest.info("Entering Contact Reason");
		String reason = msg.contactReason("Running High Fever Since Few Days");
		extentTest.info("Contact Reason Entered -" + " " + reason);
		String message = msg.message("Need to get an appointment with Doctor as soon as possible");
		extentTest.info("Message Details Entered -" + " " + message);
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Message_alert");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Message sent successfuly alert");
		msg.send();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		extentTest.info("Switching to Alert");
		Alert alert1 = driver.switchTo().alert();
		String actual = alert1.getText();
		extentTest.info("Actual Alert Text - " + " " + actual);
		String expected ="Messages Successfully sent.";
		Assert.assertEquals(actual, expected);
		alert.accept();
		extentTest.info("Launching Admin Url ");
		launchBrowser(prop.getProperty("admin_url"));
		mmpUtil.adminLogin(prop.getProperty("admin_username"), prop.getProperty("admin_password"));
		extentTest.info("Getting home Page Title -" + driver.getTitle());
		WebElement messages = driver.findElement(By.xpath("//span[normalize-space()='Messages']"));
		messages.click();
		WebElement rsn = driver.findElement(By.xpath("(//b[contains(text(),'" + reason + "')])[1]"));
		WebElement Msg = driver.findElement(By.xpath("(//td[contains(text(),'" + message + "')])[1]"));
		String reasontxt = rsn.getText();
		extentTest.info("Actual Contact Reason -" + " " + reasontxt);
		String msgtxt = Msg.getText();
		extentTest.info("Actual Message Text -" + " " + msgtxt);
		String screenshot = screenshotUtil.captureScreenshot("Reason and Message Text");
        extentTest.addScreenCaptureFromPath(screenshot,"Reason and Message Text");
		Assert.assertEquals(reasontxt, reason);
		Assert.assertEquals(msgtxt, message);
		
		
	}

}
