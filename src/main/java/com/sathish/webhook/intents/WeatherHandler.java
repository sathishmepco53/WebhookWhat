package com.sathish.webhook.intents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

public class WeatherHandler {
	
	String request;
	String geoCity;
	public WeatherHandler(String request,String geoCity) {
		this.request = request;
		this.geoCity = geoCity;
	}
	
	public String process(){
		if(geoCity == null || geoCity.trim().length() == 0)
			return defaultMessage("Please specify the city"); 
		String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text='"+geoCity+"')&format=json";

		try{
			RestTemplate restTemplate = new RestTemplate();
			String strOutput = restTemplate.getForObject(url, String.class);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(strOutput);
			JSONObject queryObject = (JSONObject) jsonObject.get("query"); 
			JSONObject resultsElt = (JSONObject) queryObject.get("results");
			JSONObject channelElt = (JSONObject) resultsElt.get("channel");
			JSONObject unitsElt = (JSONObject) channelElt.get("units");
			String tempUnit = (String) unitsElt.get("temperature");
			System.out.println(tempUnit);
			
			JSONObject locationElt = (JSONObject) channelElt.get("location");
			String city = (String) locationElt.get("city");
			System.out.println(city);
			
			JSONObject itemObject = (JSONObject)channelElt.get("item");
			JSONObject conditionObject = (JSONObject)itemObject.get("condition");
			String text = (String) conditionObject.get("text");
			String temp = (String) conditionObject.get("temp");
			
			System.out.println(text);
			System.out.println(temp);
			
			String message = "Today the weather in "+city+" is "+text+", and the temperature is "+temp+" degree Fahrenheit";
			return defaultMessage(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return defaultMessage("Sorry I didn\'t get you!");
	}

	private String defaultMessage(String message){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("apiwha_autoreply", message);
		return jsonObject.toString();
	}
}