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
			
			String event = (String) jsonObject.get("event");
			String from = (String)jsonObject.get("from");
			String to  = (String) jsonObject.get("to");
			String text = (String) jsonObject.get("text");
			
			String queryText = text.toLowerCase();
			
			if(queryText.contains("pbx") || queryText.contains("number") || queryText.contains("no")){
				PbxHandler pbxHandler = new PbxHandler(queryText);
				return pbxHandler.process();
			}else if(queryText.contains("time")){
				TimeHandler timeHandler = new TimeHandler(queryText);
				return timeHandler.process();
			}else if(queryText.contains("date")){
				DateHandler dateHandler= new DateHandler(queryText);
				return dateHandler.process();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
