package com.goeuro.uiautomation;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentTest;
import com.teachingpark.uiautomation.driver.TPDriver;
import com.teachingpark.uiautomation.model.SearchValues;
import com.teachingpark.uiautomation.properties.ReadConfigData;
import com.teachingpark.uiautomation.reports.TPReporter;
import com.teachingpark.uiautomation.reports.TPUIVerify;

/**
 * This is a base test class for all test cases
 * @author bpant12
 *
 */
public class TestBase {

	protected TPDriver objDriver;
	protected TPReporter reporter;
	protected TPUIVerify objAssert;
	protected ExtentTest test;
	protected SearchValues objSearchValues;
	
	@BeforeTest
	public void beforeTest() throws Exception{
		objDriver = new TPDriver();
		objDriver.launchBrowser(ReadConfigData.getInstance().getDefaultBrowserCode());
		objDriver.get(ReadConfigData.getInstance().getApplicationURL());
		objSearchValues = new SearchValues();
	    reporter = TPReporter.getInstance();
		objAssert = new TPUIVerify(reporter,objDriver);
	}
	
	@BeforeMethod
	public void before_method(Method method) throws Exception{
		test =	 reporter.getTestStart(method);
	}
	
	@AfterMethod
	public void afterMethod(){
		reporter.reportDone(test);
	}
	
	@AfterTest
	public void afterTest(){
		objDriver.quit();
	}
	
}
