# BP Selenium UI Automation Framework

This is a complete Selenium automation framework in Java which is capable to automate testcases with minimal coding effort.
   This framework contains:

  - Page object model Concept with Page Factory design pattern.
  - Extent report support.
  - Customized WebDriver with advance reusable functions.
  - Capability to enhance framework with minimum efforts.

## Requirements:

 - Java VM >= 1.6.0
 
## Installation & Usage:
    
    $ git clone https://github.com/bpant12/GoEuroAutomation.git
    $ cd uiautomation
    $ mvn clean compile test -Denv=<Enviornment> -Dtestngpath=<TestNG Xml File> -Ptestng-tests	

NOTE: To run sample test case use following commnd

	 $ mvn clean compile test -Denv=PRODUCTION -Dtestngpath=src/test/resources/testng/GoEuroSearch.xml -Ptestng-tests

Here the parameter:
- **Enviornment :**    will be the name of the file src/test/resources/config location. For example a **PRODUCTION.properties**  file inside config folder is representing Production attributes then in that case **Enviornment**'  will be **PRODUCTION**.

- **TestNG Xml File** : will be the testng xml file to execute your testcases. For example inside /src/test/resources/testng we have a file GoEuroSearch.xml which has all  search related test cases then **TestNG Xml File** will be **/src/test/resources/testng/GoEuroSearch.xml**.

### Note : 
  Here for different environment we can create different file i.e. STAGING.properties, DEV.properties, PRODUCTION.properties etc. 
  
  
## Test Data:
	
	$ cd src/test/resources/testData
	$ vi SEARCHDATA.properties
	
Here you can change test data

## Configuration Data:

	$ cd src/test/resources/config
	$ vi PRODUCTION.properties
	
Here we can change application URL, browser type , wait timeout etc.
	 
#Reports:

	$ cd target/SFReport 
	
Here we will find a unique folder created after execution. Inside it We will find Report.html
  
**From Bhupesh Pant, TeachingPark.com**

