package gov.iti.jets.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FriendRequestEntity {

    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private boolean status;
    private Timestamp sentAt;

    public FriendRequestEntity(Integer id, Integer senderId, Integer receiverId, boolean status, Timestamp sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
        this.sentAt = sentAt;
    }

    public FriendRequestEntity(Integer senderId, Integer receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = false;
        this.sentAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }
    public Integer getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "FriendRequestEntity{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", status=" + status +
                ", sentAt=" + sentAt +
                '}';
    }
}