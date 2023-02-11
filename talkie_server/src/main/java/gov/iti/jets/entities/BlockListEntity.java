package gov.iti.jets.entities;

import java.io.Serializable;

public class BlockListEntity implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer blockedUserId;

    public BlockListEntity(Integer id, Integer userId, Integer blockedUserId) {
        this.id = id;
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

    public BlockListEntity(Integer userId, Integer blockedUserId) {
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(Integer blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    @Override
    public String toString() {
        return "BlockListEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", blockedUserId=" + blockedUserId +
                '}';
    }
}
