package gov.iti.jets.dto.responses;

import java.io.Serializable;

public class RefuseFriendFriendResponse implements Serializable {

    private boolean refused;

    public RefuseFriendFriendResponse(boolean refused) {
        this.refused = refused;
    }

    public boolean isRefused() {
        return refused;
    }

    public void setRefused(boolean refused) {
        this.refused = refused;
    }
}
