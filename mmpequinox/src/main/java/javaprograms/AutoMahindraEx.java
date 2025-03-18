package javaprograms;

import org.iitwf.selenium.lib.FrameworkLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class AutoMahindraEx extends FrameworkLibrary{

	@Test
	public void test1() throws InterruptedException
	{
		driver.get("https://auto.mahindra.com/");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Accept All Cookies']")));
		action.click();
		action.perform();
		
		driver.findElement(By.xpath("//button[@title='Scroll to next fold']")).click();
		
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("//h3[text()='THAR ROXX']")));
		action.moveToElement(driver.findElement(By.xpath("//h3[text()='THAR ROXX']/parent::div/following-sibling::div/a[1]/div[1]/span[text()='Explore']")));
		action.click();
		
		action.perform();
	}
}
