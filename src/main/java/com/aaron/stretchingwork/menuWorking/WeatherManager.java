package com.aaron.stretchingwork.menuWorking;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// https://api.openweathermap.org/data/2.5/weather?lat=38.440791&lon=128.4470931&appid=baff8f3c6cbc28a4024e336599de28c4
public class WeatherManager {
	private final static WeatherManager WEATHERMANAGER = new WeatherManager();
	
	private WeatherManager() {
		// TODO Auto-generated constructor stub
	}

	public static WeatherManager getWeathermanager() {
		return WEATHERMANAGER;
	}
	
	public void getWeather(HttpServletRequest req) {
		HttpsURLConnection huc = null;
		try {
			String s = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=baff8f3c6cbc28a4024e336599de28c4",
									req.getParameter("lan"),
									req.getParameter("lon"));
			URL u = new URL(s);
			huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			
			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(isr);
			
			JSONObject weather = (JSONObject) ((JSONArray) jo.get("weather")).get(0);
			JSONObject main = (JSONObject) jo.get("main");
			
			Weather weatherObj = new Weather(jo.get("name") + "",
											weather.get("main") + "",
											main.get("temp") + "",
											main.get("humidity") + "",
											weather.get("icon") + "");
			
			req.getSession().setAttribute("weather", weatherObj);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
