package com.teachingpark.uiautomation.reports;

import com.teachingpark.uiautomation.constant.AppConstant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.teachingpark.uiautomation.properties.ReadConfigData;
import com.teachingpark.uiautomation.utility.Logger;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Report for functional and UI tests
 * @author bpant12
 */
public class TPReporter {

	Logger logger = new Logger().getLogger(TPReporter.class);
	ExtentReports report;
	
	String reportPath;
	
	private  TPReporter(){
		logger.enterMethod();
		reportPath = AppConstant.FUNCTIONAL_REPORT_PATH;
		report = new ExtentReports(reportPath+AppConstant.SEPARATOR+"Report.html");
		report.loadConfig(new File(AppConstant.RESOURCE_FOLDER+AppConstant.SEPARATOR+"config"+AppConstant.SEPARATOR+"Reporter.xml"));
		report.config().insertJs("$( document ).ready(function() {$('.logo-container').remove();});");
		try {
			report.addSystemInfo("Application", ReadConfigData.getInstance().getApplicationURL());
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.exitMethod();
	}

	public static TPReporter getInstance() {
		return ReportHolder.objReporter;
	}
	
	public String getReportPath(){
		return reportPath;
	}
	
	public ExtentReports getReport(){
		return report;
	}
	
	public ExtentTest getTestStart(Method method){
		logger.enterMethod();
		Test objTest  = method.getAnnotation(Test.class);
		String desc =   objTest.description();
		String[] strGroup = objTest.groups();

		ExtentTest test=null;
		if(desc!=null && desc.length()>0){
			test =	report.startTest(desc);
		}
		else{
			test =	report.startTest(method.getName());
		}

		/*Add Description*/
		test.setDescription(method.getName());

		/*Assign Group*/
		for (String string : strGroup) {
			test.assignCategory(string);
		}
			test.assignCategory(method.getDeclaringClass().getSimpleName());
		return test;
	}
	
	public void reportDone(ExtentTest test){
		report.endTest(test);
		report.flush();
	}
	
	public void flush(){
		report.flush();
	}
	
	private static class ReportHolder{
		static final TPReporter objReporter = new TPReporter();
	}
}
