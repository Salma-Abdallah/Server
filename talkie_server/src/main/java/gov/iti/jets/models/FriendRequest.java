package gov.iti.jets.models;

public class FriendRequest {

    private User sender;
    private User receiver;

    private int id;

    public FriendRequest(int id){
        sender.getId();
        receiver.getId();
        this.id = id;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}