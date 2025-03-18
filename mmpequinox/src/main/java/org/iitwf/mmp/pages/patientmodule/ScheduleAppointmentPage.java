package org.iitwf.mmp.pages.patientmodule;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javaprograms.FutureDateEx;
public class ScheduleAppointmentPage 
{
	protected WebDriver driver;
	private By newAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	private By datePickerID  =		By.id("datepicker"); 
	private String doctorSelectXpath = new String("//p[text()='Description: $specialization$']/parent::div/preceding-sibling::h4[text()='Dr.$doctorName$']/ancestor::ul/following-sibling::button");

	private ScreenshotUtil screenshotUtil;
	
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver = driver;
		if (!driver.getTitle().equals("Shedule Appointments")) {
			throw new IllegalStateException("This is not Schedule Appointment Page," +
					" current page is: " + driver.getCurrentUrl());
		}
		screenshotUtil = new ScreenshotUtil(driver);	
	}

	public  HashMap<String, String> bookAppointment(int n , String format,String specialization,String doctorName  ) throws IOException
	{

		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("doctor", doctorName);

		driver.findElement(newAppointmentButton).click();
		//driver.findElement(By.id("opener")).click();
		driver.findElement(By.xpath(doctorSelectXpath.replace("$specialization$", specialization).replace("$doctorName$",doctorName))).click();
		driver.switchTo().frame("myframe");
		driver.findElement(datePickerID).click();
		String expectedDate= FutureDateEx.generateFutureDate(n,format);
		String expectedDateArr[] = expectedDate.split("/");
		String expectedMonth = expectedDateArr[0];
		String expectedDay = expectedDateArr[1];
		String expectedYear = expectedDateArr[2];


		expectedHMap.put("date", JavaUtility.generateFutureDate(n,"MM/dd/YYYY"));

		//System.out.println("######################" + expectedMonth +"--"+ expectedDay +"---" + expectedYear );


		//Logic 
		String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		String actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();

		while(!(expectedYear.equals(actualYear)))
		{
			//System.out.println("in while year loop");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		while(!(expectedMonth.equals(actualMonth)))
		{
			//System.out.println("in while month loop");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		}
		driver.findElement(By.linkText(expectedDay)).click();
		
		 
		screenshotUtil.captureScreenshot("ScheduleAppointment_Step2_1");
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText("10Am");
		expectedHMap.put("time",timeSelect.getFirstSelectedOption().getText());

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("status"), "OK"));
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();

		driver.findElement(By.id("sym")).sendKeys("Fever and cold");
		expectedHMap.put("sym",driver.findElement(By.id("sym")).getDomProperty("value"));

		screenshotUtil.captureScreenshot("ScheduleAppointment_Step2_2");
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		return expectedHMap;


	}

}
