package org.iitwf.selenium.lib;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtility implements ITestListener {
	
	@Override
	  public void onTestSuccess(ITestResult tr) {
		System.out.println("######################TestCase Passed " + tr.getName());
		System.out.println("Total Time in Execution of TestCase Name::"+ tr.getName() +"--"+tr.getEndMillis());
		 
	}

	  @Override
	  public void onTestFailure(ITestResult tr) 
	  {
			System.out.println("######################TestCase Failed " + tr.getName());
			System.out.println("Total Time in Execution of TestCase Name::"+ tr.getName() +"--"+tr.getEndMillis());
	
	  }


}
