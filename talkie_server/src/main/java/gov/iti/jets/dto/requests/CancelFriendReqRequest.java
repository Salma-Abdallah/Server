package gov.iti.jets.dto.requests;

import java.io.Serializable;
public class FriendReqRequest implements Serializable {

    private static final long serialVersionUID= -3339255849433394L;
    private String userPhoneNumber;
    private String friendPhoneNumber;


    public FriendReqRequest(String userPhoneNumber, String friendPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        this.friendPhoneNumber = friendPhoneNumber;
    }




    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }


    public String toString() {
        return "FriendReqRequest" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}