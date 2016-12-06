package com.goeuro.uiautomation;

import java.util.List;
import org.testng.annotations.Test;

import com.teachingpark.uiautomation.pages.HomePage;
import com.teachingpark.uiautomation.pages.SearchPage;
import com.teachingpark.uiautomation.utility.GenericUtil;
import com.teachingpark.uiautomation.utility.Logger;

/**
 * Goto 
 * @author bpant12
 *
 */
public class TestGoEuroSearch extends TestBase {

	
	HomePage objHomePage;
	SearchPage objSearch;
	Logger objlogger  = new Logger().getLogger(TestGoEuroSearch.class); ;
    
	
	@Test(description="Verify GoEuro searched trip price sorting working fine")
	public void test_goeuro_searched_trip_price_sorting_working_fine() throws Exception{
		try{
			objlogger.enterMethod();
			objHomePage = new HomePage(objDriver);
			objHomePage.searchMyTrip(objSearchValues);
			objSearch = new SearchPage(objDriver);
			List<Float> objAllPrices = objSearch.getListOfAllPrices();
			boolean isSorted = GenericUtil.isSortedNumber(objAllPrices, true);
			objAssert.assertTrue(isSorted, "Prices are not sorted correctly",test);
		}
		catch(Exception e){
			objlogger.error(e.getMessage());
			objAssert.fail("Test failed due to some unknown reason-->"+ e.getMessage(),test);
		}
		finally{
			objAssert.assertAll();
		}
	}
	

}
