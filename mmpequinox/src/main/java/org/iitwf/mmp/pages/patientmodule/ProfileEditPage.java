package org.iitwf.mmp.pages.patientmodule;

import java.time.Duration;
import java.util.Random;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ProfileEditPage {
	
	WebDriver driver;
	
	int noOfChars = 5;
	int noOfDigitsZip = 5;
	int noOfDigitsAge = 2;

	By pTitle = By.xpath("//h3[normalize-space()='Personal Details']");
	By editBtn = By.xpath("//input[@id='Ebtn']");
	By firstNamePE = By.id("fname");
	By lastNamePE = By.id("lname");
	By licensePE = By.id("licn");
	By SSNPE = By.id("ssn");
	By addressPE = By.id("addr");
	By agePE = By.id("age");
	By weightPE = By.id("weight");
	By heightPE = By.id("height");
	By cityPE = By.id("city");
	By statePE = By.id("state");
	By zipCodePE = By.id("zip");
	By saveBtn = By.xpath("//input[@id='Sbtn']");
	
	
	public ProfileEditPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String enterFirstName()
	{
		String firstNameValue = "AUTFName"+ JavaUtility.generateRandomString(noOfChars);
		driver.findElement(firstNamePE).clear();
		driver.findElement(firstNamePE).sendKeys(firstNameValue);
		return firstNameValue;

	}
	
	public String enterLastName()
	{
		String lastNameValue = "AUTLName"+ JavaUtility.generateRandomString(noOfChars);
		driver.findElement(lastNamePE).clear();
		driver.findElement(lastNamePE).sendKeys(lastNameValue);
		return lastNameValue;

	}
	
	public String enterLicense()
	{
		//	String licenseValue = 9999999+ rand.nextInt(1000000)+"";
		//String licenseValue = JavaUtility.getLicNumber();
		String licenseValue = "12345678";
		driver.findElement(licensePE).clear();
		driver.findElement(licensePE).sendKeys(licenseValue);
		return licenseValue;

	}
	public String enterSSN()
	{
		String ssnValue = JavaUtility.getSSNNumber();
		driver.findElement(SSNPE).clear();
		driver.findElement(SSNPE).sendKeys(ssnValue);
		return ssnValue;

	}
	
	public String enterCity()
	{
		String cityValue = JavaUtility.getRandomCity();
		driver.findElement(cityPE).clear();
		driver.findElement(cityPE).sendKeys(cityValue);
		return cityValue;

	}
	public String enterAddressValue()
	{

		String addressValue = JavaUtility.getRandomAddress();
		driver.findElement(addressPE).clear();
		driver.findElement(addressPE).sendKeys(addressValue);
		return addressValue;

	}
	public String enterZipCodeValue()
	{

		String zipCodeValue = JavaUtility.getRandomZipCode();
		driver.findElement(zipCodePE).clear();
		driver.findElement(zipCodePE).sendKeys(zipCodeValue);
		return zipCodeValue;

	}
	
	public String enterAgeValue()
	{
		String ageValue =  JavaUtility.getRandomNoOfDigits(noOfDigitsAge)+"";
		driver.findElement(agePE).clear();
		driver.findElement(agePE).sendKeys(ageValue);
		return ageValue;

	}
	public String enterHeightValue()
	{

		String heightValue =  JavaUtility.generateRandomDigits(10,100)+"";
		driver.findElement(heightPE).clear();
		driver.findElement(heightPE).sendKeys(heightValue);
		return heightValue;

	}
	public String enterWeightValue()
	{		
		String weightValue =  JavaUtility.generateRandomDigits(10,100)+"";
		driver.findElement(weightPE).clear();
		driver.findElement(weightPE).sendKeys(weightValue);
		return weightValue;

	}
	 
	public String enterStateValue()
	{

		String state = "";

		String[] sArray = {
	            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
	            "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
	            "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
	        };

		int num = new Random().nextInt(49);
		state = sArray[num];
		driver.findElement(statePE).clear();
		driver.findElement(statePE).sendKeys(state);
		return state;
	}
	
	public void editAndUpdateProfile()
	{
		driver.findElement(editBtn).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(pTitle));
		
		String rFirstName = enterFirstName();
		String rLastName = enterLastName();
		String rLicense = enterLicense();
		String rssn = enterSSN();
		String rAddress = enterAddressValue();
		String rAge = enterAgeValue();
		String rWeight = enterWeightValue();
		String rHeight = enterHeightValue();
		String rCity = enterCity();
		String rState = enterStateValue();
		String rZipCode = enterZipCodeValue();
		
		driver.findElement(saveBtn).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		String displayedFname = driver.findElement(By.xpath("//input[@id='fname']")).getDomAttribute("value");
		String displayedLname = driver.findElement(By.xpath("//input[@id='lname']")).getDomAttribute("value");
		String displayedLic = driver.findElement(By.xpath("//input[@id='licn']")).getDomAttribute("value");
		String displayedSSN = driver.findElement(By.xpath("//input[@id='ssn']")).getDomAttribute("value");
		String displayedAdd = driver.findElement(By.xpath("//input[@id='addr']")).getDomAttribute("value");
		String displayedAge = driver.findElement(By.xpath("//input[@id='age']")).getDomAttribute("value");
		String displayedWeight = driver.findElement(By.xpath("//input[@id='weight']")).getDomAttribute("value");
		String displayedHeight = driver.findElement(By.xpath("//input[@id='height']")).getDomAttribute("value");
		String displayedCity = driver.findElement(By.xpath("//input[@id='city']")).getDomAttribute("value");
		String displayedState = driver.findElement(By.xpath("//input[@id='state']")).getDomAttribute("value");
		String displayedZipCode = driver.findElement(By.xpath("//input[@id='zip']")).getDomAttribute("value");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(displayedFname, rFirstName);
		softAssert.assertEquals(displayedLname, rLastName);
		softAssert.assertEquals(displayedLic, rLicense);
		softAssert.assertEquals(displayedSSN, rssn);
		softAssert.assertEquals(displayedAdd, rAddress);
		softAssert.assertEquals(displayedAge, rAge);
		softAssert.assertEquals(displayedWeight, rWeight);
		softAssert.assertEquals(displayedHeight, rHeight);
		softAssert.assertEquals(displayedCity, rCity);
		softAssert.assertEquals(displayedState, rState);
		softAssert.assertEquals(displayedZipCode, rZipCode);
		
		softAssert.assertAll();
		
		
	}


}
