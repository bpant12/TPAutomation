package com.teachingpark.uiautomation.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.teachingpark.uiautomation.constant.AppConstant;
import com.teachingpark.uiautomation.utility.Logger;


public class ReadConfigData {

	private static ReadConfigData objReadProperties;
	private static Properties objProperties;
	private static Logger logger = new Logger().getLogger(ReadConfigData.class);

	private ReadConfigData() {
	}

	public static ReadConfigData getInstance() throws Exception {
		if (objReadProperties == null) {
			objProperties = new Properties();
			String envConfigFileName = System.getProperty("env", "PRODUCTION").toUpperCase() + ".properties";
			File configFile = new File(AppConstant.CONFIG_PATH + AppConstant.SEPARATOR + envConfigFileName);
			if (configFile.exists()) {
				logger.info("Loading configuration from file : " + configFile.getName());
				FileInputStream f = new FileInputStream(configFile);
				objProperties.load(f);
				objReadProperties = new ReadConfigData();
			} else {
				logger.error("Configuration file : " + configFile.getAbsoluteFile() + " does not exists.");
				throw new Exception("Configuration file : " + configFile.getAbsoluteFile() + " does not exists.");
			}
		}
		return objReadProperties;
	}

	public int getImplicitWait() {
		return Integer.parseInt(objProperties.getProperty("implicit.wait.timeout"));
	}

	/**
	 * Method to return explicit wait from property
	 *
	 * @return
	 */
	public int getExplicitWait() {
		return Integer.parseInt(objProperties.getProperty("explicit.wait.timeout"));
	}

	public int getPageLoadTimeout() {
		return Integer.parseInt(objProperties.getProperty("page.load.timeout"));
	}


	public String getApplicationURL() {
		return objProperties.getProperty("application_url");
	}

	public boolean isLoggerNeeded() {
		return objProperties.getProperty("logger.enabled").trim().equals("1");

	}

	public String getDefaultBrowserCode() {
		return objProperties.getProperty("default.browser.code");
	}

	public boolean isImplicitBrowserCloseEnabled() {
		return objProperties.getProperty("implicit.browser.close").trim().equals("1");

	}

	public String getPropertyValue(String keyName) {

		String value = objProperties.getProperty(keyName);
		System.out.println(value);
		return value;
	}
}