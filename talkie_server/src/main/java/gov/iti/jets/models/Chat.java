package gov.iti.jets.models;

import java.io.Serializable;

public class Chat implements Serializable{
    private static final long serialVersionUID = 5887637195618767821L;
    protected String chatId;

    public Chat(){}

    public Chat(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

}