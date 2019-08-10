package com.whetherapp;

public class Whether {

	Double temp_min ;
	Double temp_max ;
	String weather_rain ;
	String date ;
	String applyLotion ;
	
	
	public Whether() {
		super();
	}


	public Double getTemp_min() {
		return temp_min;
	}


	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}


	public Double getTemp_max() {
		return temp_max;
	}


	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}


	public String getWeather_rain() {
		return weather_rain;
	}


	public void setWeather_rain(String weather_rain) {
		this.weather_rain = weather_rain;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getApplyLotion() {
		return applyLotion;
	}


	public void setApplyLotion(String carryUnbrella) {
		this.applyLotion = carryUnbrella;
	}


	public Whether(Double temp_min, Double temp_max, String weather_rain, String date, String carryUnbrella) {
		super();
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.weather_rain = weather_rain;
		if(weather_rain == null) {
			this.weather_rain = "No";
		}
		
		this.date = date;
		this.applyLotion = carryUnbrella;
	}




	
	
}
