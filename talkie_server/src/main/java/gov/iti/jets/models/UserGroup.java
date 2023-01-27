package gov.iti.jets.models;

public class UserGroup {

    private int user_group_id;
    private int group_chat_id;
    private int user_id;

    private Client client;

    public UserGroup(int user_id, int user_group_id, int group_chat_id) {
        super();
        this.group_chat_id = group_chat_id;
        this.user_id = user_id;
        this.user_group_id = user_group_id;
    }

    /*---------------------------------------------------------------------------------*/
    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    /*--------------------------------------------------------------------------------------*/
    public void setGroup_chat_id(int group_chat_id) {
        this.group_chat_id = group_chat_id;
    }

    public int getGroup_chat_id() {
        return group_chat_id;
    }

    /*--------------------------------------------------------------------------------------------*/
    public void setUser_id(int user_id) {
        this.group_chat_id = group_chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    /*-------------------------------------------------------------------------------------------------------------- */
    public void setClient(Client client) {
        this.client=client;;
    }
    
    /*-------------------------------------------------------------------------------------------------------------- */
    public Client getClient() {
        return client;
    }

    /*-----------------------------------------------------------------------------------------------*/
    public String toString() {
        return "User Group [User_group_id=" + user_group_id + ", Group_chat_id=" + group_chat_id + ", User_id ="
                + user_id + "]";
    }

}