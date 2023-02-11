package gov.iti.jets.dto.responses;

import java.io.Serializable;
import java.sql.Timestamp;

public class FriendReqResponse implements Serializable {

    private static final long serialVersionUID = 236875567256303352L;

    private String phoneNumber;
    private boolean status;
    private Timestamp sentAt;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public String toString() {
        return "FriendReqRequest" +
                "phoneNumber='" + phoneNumber + '\'' +
                "status='" + status + '\'' +
                "sentAt='" + sentAt + '\'' +
                '}';
    }
}
