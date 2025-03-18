package org.iitwf.selenium.lib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	WebDriver driver;
	public ScreenshotUtil(WebDriver driver)
	{
		this.driver = driver;
	}
	public String captureScreenshot(String fileName) throws IOException 
	{

		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		
		// Take the screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture the screenshot and store it as a file
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		String filePath = System.getProperty("user.dir")+"/screenshots/"+fileName+"_"+timestamp+".png";
		
		// Copy the screenshot to the specified location
		FileUtils.copyFile(srcFile, new File(filePath));
		
		return filePath;
		
		
	}
}
