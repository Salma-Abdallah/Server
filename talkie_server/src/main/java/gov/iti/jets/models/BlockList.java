package gov.iti.jets.models;

public class BlockList {
    private User userId;
    private int blockedUserId;

    public BlockList(User userId, int blockedUserId) {
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(int blockedUserId) {
        this.blockedUserId = blockedUserId;
    }
}
