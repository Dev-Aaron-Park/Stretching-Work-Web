package com.aaron.stretchingwork.menuWorking;

public class Weather {
	private String city;
	private String weather;
	private String temp;
	private String humidity;
	private String icon;
	
	public Weather() {
		// TODO Auto-generated constructor stub
	}

	public Weather(String city, String weather, String temp, String humidity, String icon) {
		super();
		this.city = city;
		this.weather = weather;
		
		this.temp = String.format("%.1f", (Double.parseDouble(temp) - 273.15)) + " กษ";
		this.humidity = humidity + " %";
		this.icon = icon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
