package gov.iti.jets.dto.responses;

import gov.iti.jets.dto.validation.Validation;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;

import java.io.Serializable;

public class SendFriendReqResponse implements Serializable {

    private FriendRequest friendRequest;
    private RegularChat regularChat;

    public SendFriendReqResponse(FriendRequest friendRequest, RegularChat regularChat) {
        this.friendRequest = friendRequest;
        this.regularChat = regularChat;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public RegularChat getRegularChat() {
        return regularChat;
    }

    public void setRegularChat(RegularChat regularChat) {
        this.regularChat = regularChat;
    }

    @Override
    public String toString() {
        return "SendFriendReqResponse{" +
                "friendRequest=" + friendRequest +
                ", regularChat=" + regularChat +
                '}';
    }
}
