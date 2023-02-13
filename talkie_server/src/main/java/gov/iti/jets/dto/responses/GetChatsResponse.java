package gov.iti.jets.dto.responses;

import gov.iti.jets.models.Chat;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.models.RegularChat;

import java.io.Serializable;
import java.util.List;

public class GetChatsResponse implements Serializable{

    private static final long serialVersionUID = 5773299112415985829L;

    private List<RegularChat> allRegularChatsList;
    private List<GroupChat> allGroupChatsList;

    public GetChatsResponse(){}

    public GetChatsResponse(List<RegularChat> allRegularChatsList, List<GroupChat> allGroupChatsList) {
        this.allRegularChatsList = allRegularChatsList;
        this.allGroupChatsList = allGroupChatsList;
    }

    @Override
    public String toString() {
        return "GetChatsResponse{" +
                "allRegularChatsList=" + allRegularChatsList +
                ", allGroupChatsList=" + allGroupChatsList +
                '}';
    }
}
