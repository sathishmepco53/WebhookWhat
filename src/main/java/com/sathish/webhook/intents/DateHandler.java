package com.sathish.webhook.intents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.json.simple.JSONObject;

public class DateHandler {
	
	String queryText;
	
	public DateHandler(String queryText){
		this.queryText = queryText;
	}
	
	public String process(){
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		if(queryText.contains("tomorrow"))
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		else if(queryText.contains("yesterday"))
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		String time = sdf.format(new Date(calendar.getTimeInMillis()));
		jsonObject.put("apiwha_autoreply", time);
		return jsonObject.toString();
	}

}
