package gov.iti.jets.models;

public class FriendRequest {

    private int senderId;
    private int receiverId;

    private int id;

    public FriendRequest(int id, int senderId, int receiverId){

        this.receiverId= receiverId;
        this.senderId = senderId;
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}