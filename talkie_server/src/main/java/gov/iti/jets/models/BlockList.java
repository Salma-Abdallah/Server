package gov.iti.jets.models;

public class BlockList {
    private User user;
    private User blockedUser;

    public BlockList() {}

    public BlockList(User user, User blockedUser) {
        this.user = user;
        this.blockedUser = blockedUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(User blockedUser) {
        this.blockedUser = blockedUser;
    }
}
