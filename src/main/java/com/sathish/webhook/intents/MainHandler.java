package com.sathish.webhook.intents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainHandler {
	String request;
	public MainHandler(String request) {
		this.request = request;
	}
	
	public String process(){
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(request);
			JSONObject queryResultObj = (JSONObject) jsonObject.get("queryResult");
			
			String queryText = (String) queryResultObj.get("queryText");
			queryText = queryText.toLowerCase();
			String action = (String) queryResultObj.get("action");
			System.out.println("Query Text is : "+queryText);
			System.out.println("Action is : "+action);
			JSONObject parameterElt = (JSONObject) queryResultObj.get("parameters");
			
			if(queryText.contains("pbx") || queryText.contains("number") || queryText.contains("no")){
				PbxHandler pbxHandler = new PbxHandler(queryText);
				return pbxHandler.process();
			}else if(queryText.contains("time")){
				TimeHandler timeHandler = new TimeHandler(queryText);
				return timeHandler.process();
			}else if(queryText.contains("date")){
				DateHandler dateHandler= new DateHandler(queryText);
				return dateHandler.process();
			}else if(action.toLowerCase().equals("myweather")){
				if(parameterElt == null || parameterElt.get("geo-city") == null)
					return defaultMessage("Please specify the city name");
				String geoCity = (String) parameterElt.get("geo-city");
				WeatherHandler weatherHandler = new WeatherHandler(request, geoCity);
				return weatherHandler.process();
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultMessage("Sorry I didn\'t get you!");
	}
	
	private String defaultMessage(String message){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fulfillmentText", message);
		return jsonObject.toString();
	}
}
