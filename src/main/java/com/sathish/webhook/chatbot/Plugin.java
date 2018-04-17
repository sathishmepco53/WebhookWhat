package com.sathish.webhook.chatbot;

public class Plugin {
        private String[] data;
        private String type;
        private String name;

        public Plugin(){

        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}