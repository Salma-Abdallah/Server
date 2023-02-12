package gov.iti.jets.dto.requests;

import gov.iti.jets.models.Chat;
import java.io.Serializable;

public class GetMessagesRequest implements Serializable{
    private static final long serialVersionUID = 213930628588567517L;
    private String phoneNumber;
    private String chatId;

    public GetMessagesRequest(){}

    public GetMessagesRequest(Chat chat){
        this.chatId = chat.getChatId();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getChatId() {
        return chatId;
    }

    @Override
    public String toString() {
        return "GetMessagesRequest{" +
                "chatId='" + chatId + '\'' +
                '}';
    }
}
