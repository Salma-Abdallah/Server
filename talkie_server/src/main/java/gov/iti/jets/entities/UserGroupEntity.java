package gov.iti.jets.entities;

import java.io.Serializable;

public class UserGroupEntity implements Serializable {

    private String userId;
    private Integer userGroupId;
    private Integer groupChatId;

    public UserGroupEntity(String userId, Integer userGroupId, Integer groupChatId) {
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.groupChatId = groupChatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
    }
}