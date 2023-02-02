package gov.iti.jets.models;

public class GroupChat {

    private String name;

    private User user;
    private Chat chat;
    /*---------------------------------------------------------------------------------------------*/
    public GroupChat(String name){

        this.name = name;
    }
    /*---------------------------------------------------------------------------------------------*/
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    /*---------------------------------------------------------------------------------------------*/
    public void setUser(User user) {
        this.user=user;
    }
    public User getUser() {
        return user;
    }
    /*---------------------------------------------------------------------------------------------*/
    public void setChat(Chat chat) {
        this.chat=chat;
    }
    public Chat getChat() {
        return chat;
    }
    /*---------------------------------------------------------------------------------------------*/



}