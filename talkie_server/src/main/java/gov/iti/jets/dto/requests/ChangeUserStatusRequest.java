package gov.iti.jets.dto.requests;

import java.io.Serializable;

public class ChangeUserStatusRequest implements Serializable{
    private String currentUserPhoneNumber;

    public ChangeUserStatusRequest(String currentUserPhoneNumber){
        this.currentUserPhoneNumber = currentUserPhoneNumber;
    }

    public String getCurrentUserPhoneNumber() {
        return currentUserPhoneNumber;
    }

    public void setCurrentUserPhoneNumber(String currentUserPhoneNumber) {
        this.currentUserPhoneNumber = currentUserPhoneNumber;
    }

    @Override
    public String toString() {
        return "ChangeUserStatusRequest [currentUserPhoneNumber=" + currentUserPhoneNumber + "]";
    }
}
