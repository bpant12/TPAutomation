package com.teachingpark.uiautomation.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.teachingpark.uiautomation.constant.AppConstant;
import com.teachingpark.uiautomation.utility.Logger;


public class ReadTestData {

	private static ReadTestData objReadProperties;
	private static Properties objProperties;
	private static Logger logger = new Logger().getLogger(ReadTestData.class);

	private ReadTestData() {
	}

	public static ReadTestData getInstance(String name) throws Exception {
		if (objReadProperties == null) {
			objProperties = new Properties();
			String dataFileName = name.toUpperCase() + ".properties";
			File dataFile = new File(AppConstant.TEST_DATA_FOLDER + AppConstant.SEPARATOR + dataFileName);
			if (dataFile.exists()) {
				logger.info("Loading data from file : " + dataFile.getName());
				FileInputStream f = new FileInputStream(dataFile);
				objProperties.load(f);
				objReadProperties = new ReadTestData();
			} else {
				logger.error("Data file : " + dataFile.getAbsoluteFile() + " does not exists.");
				throw new Exception("Data file : " + dataFile.getAbsoluteFile() + " does not exists.");
			}
		}
		return objReadProperties;
	}

	public String getPropertyValue(String keyName) {

		String value = objProperties.getProperty(keyName);
		System.out.println(value);
		return value;
	}
}