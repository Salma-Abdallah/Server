package gov.iti.jets.models;

public class BlockList {





    private int id;
    private int userId;
    private int blockedId;

    public BlockList(int id, int userId, int blockedId){

        this.id = id;
        this.blockedId=blockedId;
        this.userId = userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBlockedId(int blockedId) {
        this.blockedId = blockedId;
    }

    public int getBlockedId() {
        return blockedId;
    }
}
