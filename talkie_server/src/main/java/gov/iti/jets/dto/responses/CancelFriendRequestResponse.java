package gov.iti.jets.dto.responses;

import java.io.Serializable;

public class CancelFriendRequestResponse implements Serializable {

    private boolean cancelled;

    public CancelFriendRequestResponse(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
