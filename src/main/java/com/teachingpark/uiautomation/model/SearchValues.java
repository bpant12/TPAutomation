package com.teachingpark.uiautomation.model;

import com.teachingpark.uiautomation.properties.ReadTestData;

public class SearchValues {

	String  tripType;
	String  fromStation;
	String	toStation;
	Integer	    adults;
	Integer		children;
	Integer		infants;
	String	fromDate;
	String	toDate;
	Boolean	airbnb;
	
	public SearchValues() throws Exception{
		ReadTestData objTestData = ReadTestData.getInstance("SEARCHDATA");
		this.adults = Integer.parseInt(objTestData.getPropertyValue("Adults"));
		this.children = Integer.parseInt(objTestData.getPropertyValue("Children"));
		this.infants = Integer.parseInt(objTestData.getPropertyValue("Infants"));
		this.tripType = objTestData.getPropertyValue("TripType");
		this.fromStation = objTestData.getPropertyValue("FromStation");
		this.toStation = objTestData.getPropertyValue("ToStation");
		this.fromDate = objTestData.getPropertyValue("FromDate");
		this.toDate = objTestData.getPropertyValue("ToDate");
		this.airbnb = Boolean.parseBoolean(objTestData.getPropertyValue("Airbnb"));
	}

	public String getTripType() {
		return tripType;
	}

	public String getFromStation() {
		return fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public Integer getAdults() {
		return adults;
	}

	public Integer getChildren() {
		return children;
	}

	public Integer getInfants() {
		return infants;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public Boolean isAirbnb() {
		return airbnb;
	}
	
	
	
}
