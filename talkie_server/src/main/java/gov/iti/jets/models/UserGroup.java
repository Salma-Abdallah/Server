package gov.iti.jets.models;

public class UserGroup {

    private int userGroupId;
    private int groupChatId;



    private User user;

    public UserGroup( int userGroupId, int groupChatId) {
        user.getId();

        this.userGroupId = userGroupId;
        this.groupChatId = groupChatId;
        
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

    /*--------------------------------------------------------------------------------------------*/
    public void setUser_id(int user_id) {
        this.group_chat_id = group_chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    /*-------------------------------------------------------------------------------------------------------------- */


    public void setUser(User user) {
        this.user=user;
    }

    /*-------------------------------------------------------------------------------------------------------------- */
    public User getUser() {
        return user;
    }

}