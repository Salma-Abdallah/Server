package gov.iti.jets.models;

public class UserGroup {

    private int userGroupId;
    private int groupChatId;
    private int userId;



    public UserGroup( int userGroupId, int groupChatId, int userId) {

        this.userGroupId = userGroupId;
        this.groupChatId = groupChatId;
        this.userId = userId;

        
    }

    /*---------------------------------------------------------------------------------*/
    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    /*--------------------------------------------------------------------------------------*/
    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    /*-------------------------------------------------------------------------------------------------------------- */


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}