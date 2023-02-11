package gov.iti.jets.dto.responses;

import gov.iti.jets.models.Chat;

import java.io.Serializable;
import java.util.List;

public class GetChatsResponse implements Serializable{

    private List<Chat> allChatsList;

    public GetChatsResponse(){}

    public GetChatsResponse(List<Chat> allChatsList){
        this.allChatsList=allChatsList;
    }

    public List<Chat> getAllChatsList() {
        return allChatsList;
    }

    @Override
    public String toString() {
        return "GetChatsResponse{" +
                "AllChatsCount='" + allChatsList.size() + '\'' +
                '}';
    }
}
