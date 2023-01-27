package gov.iti.jets.models;

public class BlockList {

    private User user;
    private User blockedUser;


    private int id;

    public BlockList(int id){
        user.getId();
        blockedUser.getId();
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBlockedUserId(User blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    public User getBlockedUserId() {
        return blockedUserId;
    }
}
