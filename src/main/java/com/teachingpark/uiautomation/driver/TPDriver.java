package com.teachingpark.uiautomation.driver;


import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.teachingpark.uiautomation.constant.AppConstant.*;

import com.teachingpark.uiautomation.properties.ReadConfigData;
import com.teachingpark.uiautomation.utility.Logger;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author bpant12
 */
public class TPDriver implements WebDriver {
    private Logger logger = new Logger().getLogger(TPDriver.class);
    private WebDriver driver;

    /**
     * This method will launch the browser but won't open any url
     *
     * @param browserCode
     * @return
     * @throws Exception
     */
    public synchronized WebDriver launchBrowser(String browserCode) throws Exception {
        logger.enterMethod();
        switch (browserCode.toLowerCase()) {
        
            case "firefox":
		                FirefoxProfile fp = new FirefoxProfile();
		                fp.setPreference("javascript.enabled", true);
		                fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
		                fp.setPreference("browser.download.folderList", 2);
		                fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		                fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
		                fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		                fp.setPreference("plugin.scan.plid.all", false);
		                fp.setPreference("pdfjs.disabled", true);
		                fp.setPreference("browser.startup.page", "0");
		                driver = new FirefoxDriver(fp);
		                break;

            case "chrome":
		                String chromeDriverLinuxPath = EXTERNAL_WEBDRIVER_PATH + SEPARATOR + CHROME_LINUX_DRIVER_NAME;
		                ChromeOptions chromeOptions = new ChromeOptions();
		                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		                chromeOptions.setExperimentalOption("prefs", chromePrefs);
		                chromeOptions.addArguments("start-maximized");
		                chromeOptions.addArguments("--disable-popup-blocking");
		                HashMap<String, Object> plugin = new HashMap<String, Object>();
		                plugin.put("enabled", false);
		                plugin.put("name", "Chrome PDF Viewer");
		
		                HashMap<String, Object> prefs = new HashMap<String, Object>();
		                prefs.put("plugins.plugins_list", Arrays.asList(plugin));
		
		                chromeOptions.setExperimentalOption("prefs", prefs);
		
		                if (SystemUtils.IS_OS_WINDOWS)
				     System.setProperty(CHROME_DRIVER_COMMAND, EXTERNAL_WEBDRIVER_PATH + SEPARATOR + CHROME_WINDOWS_DRIVER_NAME);
			else if (SystemUtils.IS_OS_LINUX) {
		                    System.out.println("LINUX---------------");
		                	File f = new File(chromeDriverLinuxPath);
		                    f.setExecutable(true);
		                    System.setProperty(CHROME_DRIVER_COMMAND, chromeDriverLinuxPath);
		                } else
		                    throw new Exception("Unsupported Operating System : " + SystemUtils.OS_NAME);
		                	driver = new ChromeDriver(chromeOptions);
		                break;

            default:
                logger.warn("Your browser code - " + browserCode + " is not yet supported. So we are launching firefox as default.");
                driver = new FirefoxDriver();
                break;
        }

        setBrowserSettings();
        logger.exitMethod();
        return driver;
    }

    @Override
    public void close() {
        logger.info("Window ready to close!");
        driver.close();
    }

    @Override
    public WebElement findElement(By arg0) {
        try {
            logger.info("Element search query is-->" + arg0.toString());
            return driver.findElement(arg0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<WebElement> findElements(By arg0) {
        List<WebElement> list_Element = driver.findElements(arg0);
        logger.info("Total found elements for->" + arg0.toString() + " is-->" + list_Element.size());
        return list_Element;
    }

    @Override
    public void get(String arg0) {
        logger.info("Navigating to URL -->" + arg0);
        driver.get(arg0);
    }

    @Override
    public String getCurrentUrl() {
        String currentURL = driver.getCurrentUrl();
        logger.info("Current URL is -->" + currentURL);
        return currentURL;
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Current page title is-->" + title);
        return title;
    }

    @Override
    public String getWindowHandle() {
        logger.info("Current window name is-->" + driver.getWindowHandle());
        return driver.getWindowHandle();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    @PreDestroy
    public void quit() {
        logger.info("Driver ready to quit!");
        driver.quit();
    }

    @Override
    public TargetLocator switchTo() {
        logger.info("Driver ready to Switch!");
        return driver.switchTo();
    }

    /**
     * get current web driver instance
     *
     * @return
     * @method getDriver
     * @author bpant12
     */
    public WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            logger.error("Driver not initialized");
            return null;
        }
    }


    /**
     * Wait till update web page element in HTML DOM
     *
     * @param element
     * @param waitSec
     * @return type void
     * @throws StaleElementReferenceException
     * @throws NoSuchElementException
     * @throws Exception
     * @method waitStalenessOf
     * @author bpant12
     */
    public void waitStalenessOf(WebElement element, int waitSec) throws Exception {
        logger.enterMethod();
        try {
            WebDriverWait wait = new WebDriverWait(this.getDriver(), waitSec, 1);
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("ELEMENT IS NOT DELETED FROM GUI->" + e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element not found->" + element);
        } catch (Exception e) {
            throw new Exception("Some error in function->" + Arrays.toString(e.getStackTrace()));
        }
        logger.exitMethod();
    }

    /**
     * Select web element from list of elements on the basis of partial matched text(Ignore case)
     *
     * @param lstElementList
     * @param sValueToBeSelected
     * @return
     * @throws Exception
     * @method selectFromListByPartialText
     * @author bpant12
     */
    public boolean selectFromListByPartialText(List<WebElement> lstElementList, String sValueToBeSelected) throws Exception {
        logger.enterMethod();
        boolean flag = false;
        try {
            logger.info("Total element found " + lstElementList.size() + " and Value to be selected " + sValueToBeSelected + " from list" + lstElementList);

            for (WebElement e : lstElementList) {
                if (e.getText().toLowerCase().contains(sValueToBeSelected.toLowerCase())) {
                    logger.info("Value found in the list as->" + e.getText());
                    this.click(e);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new Exception("No match found in the list for value->" + sValueToBeSelected);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

        return flag;
    }

    /**
     * Select web element from list of elements on the basis of complete matched text(Ignore case)
     *
     * @param lstElementList
     * @param sValueToBeSelected
     * @return
     * @throws Exception
     * @method selectFromListByCompleteText
     * @author bpant12
     */
    public boolean selectFromListByCompleteText(List<WebElement> lstElementList, String sValueToBeSelected) throws Exception {
        logger.enterMethod();
        boolean flag = false;
        try {
            logger.info("Total element found " + lstElementList.size() + " and Value to be selected " + sValueToBeSelected + " from list" + lstElementList);

            for (WebElement e : lstElementList) {
                if (e.getText().equalsIgnoreCase(sValueToBeSelected)) {
                    logger.info("Value found in the list as->" + e.getText());
                    this.click(e);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new Exception("No match found in the list for value->" + sValueToBeSelected);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

        return flag;
    }


   
    /**
     * Load all web element text in a list
     *
     * @param elements
     * @return
     * @method getTextFromElementInList
     * @author bpant12
     */
    public List<String> getTextFromElementInList(List<WebElement> elements) {
        List<String> values = new ArrayList<>();
        logger.enterMethod();
        for (WebElement webElement : elements) {
            values.add(webElement.getText());
            System.out.println(webElement.getText());
        }
        logger.exitMethod();
        return values;
    }

 /**
  * Click a web element using java script
  * @param objElement
  */
   public void clickByJS(WebElement objElement){
	   JavascriptExecutor executor = (JavascriptExecutor)driver;
	   executor.executeScript("arguments[0].click();", objElement);
   }
   
   public void clickOnCordinate(int x,int y){
	   JavascriptExecutor executor = (JavascriptExecutor)driver;
	   executor.executeScript("document.elementFromPoint("+x+","+y+").click()");
   }
   
   
   public void submitFormByID(String id){
	   JavascriptExecutor executor = (JavascriptExecutor)driver;
	   executor.executeScript("document.getElementById('"+id+"').submit();");
   }
   
   public void clickByID(String id){
	   JavascriptExecutor executor = (JavascriptExecutor)driver;
	   executor.executeScript("document.getElementById('"+id+"').click();");
   }
   
   
   
   /**
    * set text in a web element using java script
    * @param objElement
    */
     public void sendKeysByJS(WebElement objElement,String text){
  	   JavascriptExecutor executor = (JavascriptExecutor)driver;
  	   executor.executeScript("arguments[0].setAttribute('value', '" + text +"')", objElement);
     }
     
     /**
      * change style of a web element using java script
      * @param objElement
      */
       public void changeStyleOfElement(WebElement objElement,String text){
    	   JavascriptExecutor executor = (JavascriptExecutor)driver;
    	   executor.executeScript("arguments[0].setAttribute('style', '" + text +"')", objElement);
       }
       

    /**
     * This method will check if an element is present or not @ that particular instant ( without any wait )
     *
     * @param by
     * @return
     * @throws Exception
     * @author bpant12
     */
    public boolean isElementPresent(By by) throws Exception {
        return isElementPresent(by, 0);
    }

    /**
     * check if web element present on screen
     *
     * @param by
     * @param timeOut
     * @return
     * @throws Exception
     * @method isElementPresent
     *  @author bpant12
     */
    public boolean isElementPresent(final By by, int timeOut) throws Exception {
        logger.enterMethod();
        boolean isPresent = false;
        disableImplicitWait();
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut, 5);
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            logger.info("Waiting for element to be visible");
            isPresent = true;
        } catch (TimeoutException e) {
            isPresent = false;
        } finally {
            resetImplicitTimeout();
        }
        logger.exitMethod();
        return isPresent;
    }


    /**
     * is wen element present on screen
     *
     * @param elementParent
     * @param by
     * @param timeOut
     * @return
     * @throws Exception
     * @method isElementPresent
     *  @author bpant12
     */
    public boolean isElementPresent(final WebElement elementParent, final By by, int... timeOut) throws Exception {
        logger.enterMethod();
        boolean isPresent = false;
        disableImplicitWait();
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut.length == 1 ? timeOut[0] : 0, 10);
        try {
            webDriverWait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    return elementParent.findElements(by).size() > 0;
                }
            });
            isPresent = true;
        } catch (TimeoutException ex) {
            isPresent = false;
        } finally {
            resetImplicitTimeout();
        }
        logger.exitMethod();
        return isPresent;
    }

    /**
     * Submit form
     *
     * @param element
     * @return type void
     * @throws Exception
     * @method submit
     * @author bpant12
     */
    public void submit(WebElement element) throws Exception {
        logger.enterMethod();
        element.submit();
        logger.exitMethod();

    }

    /**
     * Refresh web page
     *
     * @return type void
     * @throws Exception
     * @method refresh
     * @author bpant12
     */
    public void refresh() {
        logger.enterMethod();
        driver.navigate().refresh();
        logger.exitMethod();
    }

    /**
     * goto back page
     *
     * @return type void
     * @throws Exception
     * @method back
     * @author bpaant12
     */
    public void back() {
        logger.enterMethod();
        driver.navigate().back();
        logger.exitMethod();
    }

    /**
     * @param element
     * @return type void
     * @throws Exception
     * @method click
     * @author bpant12
     */
    public void click(WebElement element) throws Exception {
        logger.enterMethod();
        this.waitForElementToBeDisplayed(element);
        logger.info("Element to be clickable is -->" + element);
        element.click();
        logger.exitMethod();
    }


    /**
     * Set text on a web element
     *
     * @param element
     * @param values
     * @return type void
     * @throws InterruptedException
     * @throws IOException
     * @method sendKeys
     * @author bpant12
     */
    public void sendKeys(WebElement element, CharSequence... values) throws InterruptedException, IOException {
        logger.enterMethod();
        boolean flag = false;
        int i = 1;
        CharSequence[] newValue = new CharSequence[values.length - 1];
        StringBuilder buildString = new StringBuilder();
        for (CharSequence charSequence : values) {
            buildString.append(charSequence);
            if (values[0].toString().equalsIgnoreCase("password")) {
                flag = true;
                newValue[i - 1] = values[i];
            } else {
                newValue = values;
                break;
            }
        }
        element.sendKeys(newValue);
        if (!flag) {
            logger.info("Text entered as->" + buildString.toString());
        }
        logger.exitMethod();
    }

    /**
     * This method will clear and set text in a text field
     *
     * @param element
     * @param values
     * @throws IOException
     * @throws InterruptedException
     * @author bpant12
     */
    public void setText(final WebElement element, CharSequence... values) throws IOException, InterruptedException {
        element.clear();
        this.sendKeys(element, values);
    }

    /**
     * @param element
     * @return type void
     * @throws InterruptedException
     * @throws IOException
     * @method clear
     * @author bpant12
     */
    public void clear(WebElement element) throws InterruptedException, IOException {
        logger.enterMethod();
        element.clear();
        logger.exitMethod();
    }

   

   

    /**
     * Wait for element to be appear from web page
     *
     * @param element
     * @param maxSecondTimeout
     * @param isFailOnException
     * @return
     * @throws Exception
     * @method waitForElementToBeDisplayed
     * @author bpant12
     */
    public boolean waitForElementToBeDisplayed(final WebElement element, int maxSecondTimeout, boolean... isFailOnException) throws Exception {
        logger.enterMethod();
        WebDriverWait webDriverWait = new WebDriverWait(driver, maxSecondTimeout);
        try {
            return webDriverWait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    logger.exitMethod();
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            logger.exitMethod();
            if (isFailOnException.length > 0 && isFailOnException[0])
                throw e;
            else
                return false;
        }
    }

    /**
     * This method will wait till the element is displayed
     *
     * @param element
     * @throws Exception
     */
    public void waitForElementToBeDisplayed(final WebElement element) throws Exception {
        waitForElementToBeDisplayed(element, ReadConfigData.getInstance().getImplicitWait());
    }

  

    private synchronized void setBrowserSettings() throws Exception {
        logger.enterMethod();
        setImplicitWait(Integer.parseInt(ReadConfigData.getInstance().getPropertyValue("implicit.wait.timeout")), TimeUnit.SECONDS);
        logger.info("Implicit Timeout set as ->" + ReadConfigData.getInstance().getPropertyValue("implicit.wait.timeout") + " Sec.");
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ReadConfigData.getInstance().getPropertyValue("implicit.wait.timeout")), TimeUnit.SECONDS);
        logger.info("Pageload Timeout set as ->" + ReadConfigData.getInstance().getPropertyValue("implicit.wait.timeout") + " Sec.");
        logger.info("Browser window maximized");
        //driver.manage().window().setSize(new Dimension(1024,768));
        driver.manage().window().maximize();
        logger.exitMethod();
    }

    /**
     * This method will wait till page loads
     *
     * @author bpant12
     * @throws Exception 
     */
    public void waitTillPageIsLoaded() throws Exception {
        logger.info("Waiting for page load event to finish");
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        logger.info("Waiting till document is in ready state");
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, ReadConfigData.getInstance().getPageLoadTimeout());
        wait.until(pageLoadCondition);
    }

    /**
     * This method will grab the screenshot
     *
     * @return
     */
    public byte[] takeScreenShot() {
        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
  
    /**
     * This method will reset the implicit wait to default ( as set in properties file )
     * @throws Exception 
     */
    public void resetImplicitTimeout() throws Exception {
        logger.info("Resetting implicit timeout to default");
        setImplicitWait(ReadConfigData.getInstance().getImplicitWait(), TimeUnit.SECONDS);
    }

    /**
     * This method will set implicit wait for selenium and also for custom html elements
     *
     * @param timeout
     * @param timeUnit
     * @throws SyncAppException
     * @throws IOException
     * @author bpant12
     */
    public void setImplicitWait(int timeout, TimeUnit timeUnit) throws  IOException {
        System.setProperty("webdriver.timeouts.implicitlywait", String.valueOf(timeout));
        driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }

    /**
     * This method will remove implicit wait
     *
     * @throws SyncAppException
     * @throws IOException
     * @author bpant12
     */
    public void disableImplicitWait() throws  IOException {
        setImplicitWait(0, TimeUnit.SECONDS);
    }

    /**
     * This method will return text from webelement
     *
     * @param element
     * @return
     */
    public String getText(WebElement element) {
        return element.getText().trim();
    }
    
    /**
     * Wait for element to be clickable 
     * @param element
     */
    public void waitForElementToBeClickable(WebElement element) {
        logger.enterMethod();
        WebDriverWait wait = new WebDriverWait(this.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.exitMethod();
    }
    
    /**
     * This method will wait till the element count to exceed the expected count
     *
     * @param elementLocator
     * @param expCount
     * @throws IOException
     */
    public void waitForElementCount(final By elementLocator, final int expCount) throws IOException {
        try {
            new WebDriverWait(this.getDriver(), ReadConfigData.getInstance().getImplicitWait(), 1).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() >= expCount);
                }
            });

        } catch (NoSuchElementException e) {
            logger.error(e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public boolean waitForElementToBeHidden(WebElement element, int maxSecondTimeout, boolean... isFailOnException) throws Exception {
        logger.enterMethod();
        try {
            maxSecondTimeout = maxSecondTimeout * 20;
            while (element.isDisplayed() && (maxSecondTimeout > 0)) {
                Thread.sleep(50l);
                maxSecondTimeout--;
            }
            if ((maxSecondTimeout == 0) && (isFailOnException.length != 0)) {
                if (isFailOnException[0] == true) {
                    Exception e = new Exception("Element is not hidden within " + (maxSecondTimeout / 20) + "Sec.");
                    logger.error(e);
                    throw e;
                }
            }
            logger.exitMethod();
           
        }catch (NoSuchElementException e) {
            //logger.error(e);
            //throw e;
        } 
        catch (StaleElementReferenceException e) {
           // logger.error(e);
           // throw e;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return true;
    }
}
