package gov.iti.jets.dto.requests;

import java.io.Serializable;
public class FriendReqRequest implements Serializable {

    private static final long serialVersionUID= -3339255849433394L;




    private String userPhoneNumber;
    private String friendPhoneNumber;
    private boolean status;

    //    private UserEntity sender;
//    private UserEntity receiver;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return "FriendReqRequest" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                "status='" + status + '\''+
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +

                '}';
    }
}