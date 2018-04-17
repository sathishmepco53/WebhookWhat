package com.sathish.webhook.chatbot;

import java.util.ArrayList;

/**
 * Created by Seng on 2/27/2018.
 */

public class OutputMessage {

    private String messageSource;
    private String[] messageText;
//    private ArrayList<String[]> messageText;
    private String user_id;
    private String state;
    private String city;
    private String domain;
    private Plugin plugin;

    public OutputMessage(){

    }

    public String getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

/*	public ArrayList<String[]> getMessageText() {
		return messageText;
	}

	public void setMessageText(ArrayList<String[]> messageText) {
		this.messageText = messageText;
	}*/
    

    public String[] getMessageText() {
        return messageText;
    }

    public void setMessageText(String[] messageText) {
        this.messageText = messageText;
    }
}