package com.sathish.webhook.chatbot;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class ChatBotIntent {
	
	private String queryText;
	private String fromPhoneNo;
	
	public ChatBotIntent(String queryText,String fromPhoneNo) {
		this.queryText = queryText;
		this.fromPhoneNo = fromPhoneNo;
	}
	
	public String process(){
		
		InputMessage inputMessage = new InputMessage();
		inputMessage.setMessageText(queryText);
		inputMessage.setUser_id(fromPhoneNo);
		inputMessage.setMessageSource(Constants.MESSAGE_FROM_USER);
		String outputContentToWhatsapp = "";
		Gson gson = new Gson();
		String jsonContent = gson.toJson(inputMessage);
		String outputJson ="";
		try{
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<String>(jsonContent,headers);
			outputJson = restTemplate.postForObject(Constants.URL, entity, String.class);
			System.out.println("Output from chatbot : "+outputJson);
		}catch(Exception e){
			e.printStackTrace();
			outputContentToWhatsapp = "Sorry, could not connect to ChatBot.";
		}
		try{
				OutputMessage outputMessage = gson.fromJson(outputJson, OutputMessage.class);
				outputContentToWhatsapp = outputMessage.getMessageText()[0]+"\n";
//				outputContentToWhatsapp = outputMessage.getMessageText().get(0)[0]+"\n";
				if(outputMessage.getPlugin() != null && outputMessage.getPlugin().getData() != null && outputMessage.getPlugin().getData().length >0){
					for(String str : outputMessage.getPlugin().getData())
						outputContentToWhatsapp = outputContentToWhatsapp + "\n - "+str;
				}
		}catch(Exception e){
			e.printStackTrace();
			outputContentToWhatsapp = "Sorry, could not parse message from chatbot.";
		}
		return getJSONtoWhatsapp(outputContentToWhatsapp);
	}
	
	private String getJSONtoWhatsapp(String message){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("apiwha_autoreply", message);
		return jsonObject.toString();
	}
}