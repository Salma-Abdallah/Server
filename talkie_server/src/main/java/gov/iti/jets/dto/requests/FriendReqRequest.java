package gov.iti.jets.dto.requests;

import java.io.Serializable;
public class FriendReqRequest implements Serializable {

    private static final long serialVersionUID= -3339255849433394L;


    private String phoneNumber;

//    private UserEntity sender;
//    private UserEntity receiver;
    private boolean status;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber () {
                return phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return "FriendReqRequest" +
                "phoneNumber='" + phoneNumber + '\'' +
                "status='" + status + '\''+
                '}';
    }
}