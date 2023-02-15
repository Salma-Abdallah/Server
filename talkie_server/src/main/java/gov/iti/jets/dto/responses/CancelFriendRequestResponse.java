package gov.iti.jets.dto.responses;

import java.io.Serializable;

public class CancelFriendRequestResponse implements Serializable {

    private String alert;

    public CancelFriendRequestResponse(String alert) {
        this.alert = alert;
    }
    @Override
    public String toString() {
        return "CancelFriendRequestResponse{" +
                "alert='" + alert + '\'' +
                '}';
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
