package com.teachingpark.uiautomation.constant;

import java.io.File;
import java.util.Random;

import static java.lang.System.getProperty;

public interface AppConstant {

    File PARENT_DIR = new File(getProperty("user.dir"));
    String SEPARATOR = System.getProperty("file.separator");
    String RESOURCE_FOLDER = PARENT_DIR.getPath() + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources";
    String TEST_DATA_FOLDER = PARENT_DIR.getPath() + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources" + SEPARATOR + "testData";
    String CONFIG_PATH = RESOURCE_FOLDER + SEPARATOR + "config";
    String EXTERNAL_WEBDRIVER_PATH = RESOURCE_FOLDER + SEPARATOR + "drivers";

    String CHROME_WINDOWS_DRIVER_NAME = "chromedriver.exe";
    String CHROME_LINUX_DRIVER_NAME = "chromedriver";
    String IE_32_BIT_DRIVER_NAME = "IEDriverServer32Bit.exe";
    String IE_64_BIT_DRIVER_NAME = "IEDriverServer64Bit.exe";
    String PHANTOMJS_WINDOWS_DRIVER_NAME = "phantomjs.exe";
    String PHANTOMJS_LINUX_DRIVER_NAME = "phantomjs";
    String SAFARI_WINDOWS_DRIVER_NAME = "safari";
    String CHROME_DRIVER_COMMAND = "webdriver.chrome.driver";
    String IE_DRIVER_COMMAND = "webdriver.ie.driver";
    String PHANTOMJS_DRIVER_COMMAND = "phantomjs.binary.path";
    String LOG_CONFIGURATION = RESOURCE_FOLDER + SEPARATOR + "config" + SEPARATOR + "log4j-agent.xml";
    String WEBSERVICE_REQUEST_PATH = RESOURCE_FOLDER + SEPARATOR + "testData" + SEPARATOR + "propData";
    String MAIL_MONGO_COLLECTION="mailtrigger";
    String OBJECT_BANK_COLLECTION="objectbank";
    String TEST_STEPS_COLLECTION="teststeps";
    String TEST_CASES_COLLECTION="testcases";
    String FUNCTIONAL_REPORT_PATH = PARENT_DIR.getPath() + SEPARATOR + "target" + SEPARATOR + "SFReport" + SEPARATOR + "SFReport" + new Random().nextInt();
}
