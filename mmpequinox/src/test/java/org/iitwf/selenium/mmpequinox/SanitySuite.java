package org.iitwf.selenium.mmpequinox;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


public class SanitySuite extends FrameworkLibrary {
	
	private ExtentTest extentTest;
	
	@Test
	public void performHealthCheckTests()
	{	
		extentTest = extentReports.createTest("######## SanitySuite Test ########");
		extentTest.info("Launching URL");
		launchBrowser(prop.getProperty("patient_url"));
		
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		ArrayList<String> expectedMenuArrayList = readMenuTitles();
		ArrayList<String> actualMenuArrayList = fetchMenuTitlesFromUI();
		System.out.println("Expected Array List" + expectedMenuArrayList);
		System.out.println("Actual Array List" + actualMenuArrayList);
		
		Assert.assertTrue(actualMenuArrayList.equals(expectedMenuArrayList));
		extentTest.info("Screen Shots captured - Test Complete");
		
		   
	}
	
	public ArrayList<String> fetchMenuTitlesFromUI() {
	    ArrayList<String> actualMenuArrayList = new ArrayList<>();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Fetch total elements count initially
	    int totalElements = driver.findElements(By.xpath("//div[@class='sidebar-holder']/ul/li/a/span")).size();

	    for (int i = 0; i < totalElements; i++) {
	        try {
	            // Locate the element dynamically
	            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("(//div[@class='sidebar-holder']/ul/li/a/span)[" + (i + 1) + "]")
	            ));

	            // Retrieve text before clicking
	            String menuText = element.getText().trim();
	            if (!menuText.isEmpty()) {
	                actualMenuArrayList.add(menuText);
	                System.out.println("Added to list: " + menuText);
	            } else {
	                System.out.println("Text is empty for index: " + i);
	            }
	            
	            ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
	            String screenshotPath = screenshotUtil.captureScreenshot("" + element.getText().trim() +"");
	            extentTest.addScreenCaptureFromPath(screenshotPath,"" + element.getText().trim() +"");

	            // Scroll to element and click
	            js.executeScript("arguments[0].scrollIntoView(true);", element);
	            Thread.sleep(500); // Wait for DOM to stabilize
	            js.executeScript("arguments[0].click();", element);

	        } catch (StaleElementReferenceException e) {
	            System.out.println("Stale element encountered. Re-fetching DOM for index: " + i);
	            totalElements = driver.findElements(By.xpath("//div[@class='sidebar-holder']/ul/li/a/span")).size();
	            i--; // Retry the current index
	        } catch (Exception e) {
	            System.out.println("Unexpected exception: " + e.getMessage());
	        }
	    }

	    Collections.sort(actualMenuArrayList);
	    return actualMenuArrayList;
	}

 	public static ArrayList<String> readMenuTitles()
	{

		ArrayList<String> expectedMenuArrayList= new ArrayList<String>();
		expectedMenuArrayList.add("HOME");
		expectedMenuArrayList.add("Profile");
		expectedMenuArrayList.add("Schedule Appointment");
		expectedMenuArrayList.add("Information");
		expectedMenuArrayList.add("Fees");
		expectedMenuArrayList.add("Search Symptoms");
		expectedMenuArrayList.add("Messages");
		expectedMenuArrayList.add("Logout");
		//Arrays.sort(expectedMenuArrayList.toArray());
		Collections.sort(expectedMenuArrayList);
		return expectedMenuArrayList;
	}
	 
}
 