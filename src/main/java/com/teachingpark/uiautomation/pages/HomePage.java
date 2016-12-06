package com.teachingpark.uiautomation.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.teachingpark.uiautomation.driver.TPDriver;
import com.teachingpark.uiautomation.model.SearchValues;
import com.teachingpark.uiautomation.properties.ReadConfigData;

/**
 * This is the Landing Page of GoEuro
 * @author bpant12
 *
 */

public class HomePage {

	TPDriver driver;
	
	/***
	 *  ================== Search Section ===============================
	 */
	
	@FindBy(css=".search-types-bar li")
	private List<WebElement> list_dropdown_TripType;
	
	
	@FindBy(id="from_filter")
	private WebElement textbox_TripFrom;
	
			@FindBy(css="#ui-id-1 li.ui-menu-item")
			private List<WebElement> list_li_TripFromSuggetions;
			
	
	@FindBy(id="to_filter")
	private WebElement textbox_TripTo;
	
			@FindBy(css="#ui-id-2 li.ui-menu-item")
			private List<WebElement> list_li_TripToSuggetions;
	
			
	@FindBy(id="person-counter")
	private WebElement button_PersonCounter;
	
			@FindBy(id="nbadults")
			private WebElement textbox_AdultCount;
			
			@FindBy(id="nbchildren")
			private WebElement textbox_ChildrenCount;
			
			@FindBy(id="nbinfants")
			private WebElement textbox_InfantsCount;
			
	
	@FindBy(id="departure_date")
	private WebElement textbox_DepartureDate;
			
	@FindBy(id="return_date")
	private WebElement textbox_ReturnDate;
	
	@FindBy(xpath="//div[@class='twelve'][input[@id='search-form__submit-btn']]")
	private WebElement button_Search;
	
	@FindBy(css="div[class='hotel-checkbox'] label.hotel-checkbox__airbnb span")
	private WebElement checkbox_Airbnb;
	
	/***
	 *  ==================End Search Section ===============================
	 */
	
	@FindBy(id="popover-bg")
	private WebElement div_PopupForTripMemberCount;
	
    public HomePage(TPDriver driver) throws Exception {
    	this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, ReadConfigData.getInstance().getImplicitWait());
        PageFactory.initElements(factory, this);
    }
    
    /**
     * Search the trip with all required information
     * @param objSearchValues
     * @throws Exception
     */
    public void searchMyTrip(SearchValues objSearchValues) throws Exception{
    	driver.sendKeys(textbox_TripFrom, objSearchValues.getFromStation());
    	driver.sendKeys(textbox_TripTo, objSearchValues.getToStation());
        driver.click(checkbox_Airbnb);
    	driver.sendKeysByJS(textbox_DepartureDate, objSearchValues.getFromDate());
    	if(objSearchValues.getTripType().toLowerCase().trim().equals("round-trip"))
    	{
    		driver.selectFromListByCompleteText(list_dropdown_TripType, objSearchValues.getTripType());
    		driver.sendKeysByJS(textbox_ReturnDate, objSearchValues.getToDate());
    	}
    	fillPeopleCount(objSearchValues);
    	/**
    	 * NOTE: Here I am clicking Search button 2 times  at the time 	of a	utomation button
    	 * is not performing click behaviour properly ( Selenium is clicking but nothing happening
    	 * first time).Its a hack to run automation there can be other ways to solve it also.
    	 */
    	button_Search.click();
    	driver.waitForElementToBeClickable(button_Search);
    	button_Search.click();
    }
    
    /**
     * a private method to fill count of the people traveling
     * @param objSearchValues
     * @throws Exception
     */
    private void fillPeopleCount(SearchValues objSearchValues) throws Exception{
    	driver.click(button_PersonCounter);
    	driver.clear(textbox_AdultCount);
    	driver.sendKeys(textbox_AdultCount ,objSearchValues.getAdults().toString());
    	driver.clear(textbox_ChildrenCount);
    	driver.sendKeys(textbox_ChildrenCount, objSearchValues.getChildren().toString());
    	driver.clear(textbox_InfantsCount);
    	driver.sendKeys(textbox_InfantsCount, objSearchValues.getInfants().toString());
    	driver.clickOnCordinate(0,0);
    	driver.waitForElementToBeHidden(textbox_InfantsCount,10);
    }
}
