package com.sathish.webhook.intents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.json.simple.JSONObject;

public class TimeHandler {
	
	String queryText;
	
	public TimeHandler(String queryText){
		this.queryText = queryText;
	}
	
	public String process(){
		JSONObject jsonObject = new JSONObject();
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		SimpleDateFormat sdf = new SimpleDateFormat("hh mm a");
		String time = sdf.format(new Date());
		if(time.trim().startsWith("00"))
			time = time.replace("00", "12");
		else if(time.trim().startsWith("0"))
			time = time.replace("0", "\\s");
		jsonObject.put("fulfillmentText", time);
		return jsonObject.toString();
	}
}
