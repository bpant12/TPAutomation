package com.teachingpark.uiautomation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.teachingpark.uiautomation.driver.TPDriver;
import com.teachingpark.uiautomation.properties.ReadConfigData;

public class SearchPage {

	@FindBy(css="div[class*='Result__header___'] div[class*='Result__priceContainer___']")
	private List<WebElement> list_div_Price;
	
	@FindBy(css="a[class*='ResultTabs__activeTab___'] svg[class*='SmallSpinner__spinner___']")
	private WebElement loader;
	
	By objLoader = By.cssSelector("a[class*='ResultTabs__activeTab___'] svg[class*='SmallSpinner__spinner___']");
	
	TPDriver driver;
	
    public SearchPage(TPDriver driver) throws Exception {
    	this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, ReadConfigData.getInstance().getImplicitWait());
        PageFactory.initElements(factory, this);
    }
    
    /**
     * give list of all prices of a trip
     * @return
     * @throws Exception 
     */
    public List<Float> getListOfAllPrices() throws Exception{
    	driver.waitForElementCount(objLoader, 0);
    	List<String> objAllPriceText = driver.getTextFromElementInList(list_div_Price);
    	List<Float> objPriceValues = new ArrayList<>();
    	for (String price : objAllPriceText) {
    		objPriceValues.add(Float.valueOf(price.replace(",", "").substring(1)));
		}
    	return objPriceValues;
    }
}
