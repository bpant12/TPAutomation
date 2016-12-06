package com.teachingpark.uiautomation.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.asserts.IAssert;

import com.teachingpark.uiautomation.constant.AppConstant;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.teachingpark.uiautomation.driver.TPDriver;
import com.teachingpark.uiautomation.utility.GenericUtil;
/**
 *Class  for UI related verification
 * @author bpant12
 */
public class  TPUIVerify extends TPSoftAssert {

	TPDriver driver;
	public TPUIVerify(TPReporter report, TPDriver driver) {
		super(report);
		this.driver =driver;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doAssert(IAssert<?> assertCommand,ExtentTest test) {
		onBeforeAssert(assertCommand);
	    Object a = assertCommand.getActual();
	    Object b = assertCommand.getExpected();
	    String m = assertCommand.getMessage();
	    if(m!=null &&  m.length()>0){
	  	  m = "FOR TEST STEP[  "+m+"  ]";
	    }
	    else{
	    	m = "MISSING STEP==";
	    }
	    try {
	      executeAssert(assertCommand);
	     
	      test.log(LogStatus.PASS, m+"ACTUAL VALUE IS:[  "+a+"  ] AND EXPECTED VALUE IS:[  "+b+"  ]");
	      onAssertSuccess(assertCommand);
	    } catch(AssertionError ex) {
	      onAssertFailure(assertCommand, ex);
	      m_errors.put(ex, assertCommand);
	      byte[] image = driver.takeScreenShot();
	     String IMAGEPATH =report.getReportPath() +AppConstant.SEPARATOR+m+GenericUtil.getRandom()+".png";
	      try {
			FileUtils.writeByteArrayToFile(new File(IMAGEPATH), image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	      String snapshot = test.addScreenCapture(IMAGEPATH);
	      test.log(LogStatus.FAIL, m+ex.getMessage()+"\n\n\n\n"+"Snapshot below: " + snapshot );
	     // throw ex;
	    } finally {
	      onAfterAssert(assertCommand);
	    }
	  }

}
