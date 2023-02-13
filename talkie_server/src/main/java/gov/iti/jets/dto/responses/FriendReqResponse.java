package gov.iti.jets.dto.responses;

import gov.iti.jets.models.FriendRequest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class FriendReqResponse implements Serializable {

    private static final long serialVersionUID = 236875567256303352L;

    private String userPhoneNumber;

    private String friendPhoneNumber;

    private boolean status;
    private Timestamp sentAt;

    private  int result;

    private List<FriendRequest> friendRequestList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }






























































































































































































































































































































































































































    public List<FriendRequest> getFriendRequestList() {
        return friendRequestList;
    }

    public void setFriendRequestList(List<FriendRequest> friendRequestList) {
        this.friendRequestList = friendRequestList;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public FriendReqResponse(int result){
        this.result = result;
    }
    public FriendReqResponse(List<FriendRequest>friendRequestList){
        this.friendRequestList = friendRequestList;
    }



    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }


    public Timestamp getSentAt() {
        return sentAt;
    }

    public String toString() {
        return "FriendReqRequest" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                "status='" + status + '\''+
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +

                '}';
    }
}
