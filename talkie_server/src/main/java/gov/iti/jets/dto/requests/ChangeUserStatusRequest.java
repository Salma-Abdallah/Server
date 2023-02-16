package gov.iti.jets.dto.requests;

import java.io.Serializable;

public class ChangeUserStatusRequest implements Serializable{
    private String currentUserPhoneNumber;
    private String status;

    public ChangeUserStatusRequest(String currentUserPhoneNumber, String status) {
        this.currentUserPhoneNumber = currentUserPhoneNumber;
        this.status = status;
    }

    public String getCurrentUserPhoneNumber() {
        return currentUserPhoneNumber;
    }

    public void setCurrentUserPhoneNumber(String currentUserPhoneNumber) {
        this.currentUserPhoneNumber = currentUserPhoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChangeUserStatusRequest{" +
                "currentUserPhoneNumber='" + currentUserPhoneNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
