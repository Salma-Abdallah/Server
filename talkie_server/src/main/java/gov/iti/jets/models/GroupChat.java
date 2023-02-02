package gov.iti.jets.models;

public class GroupChat {

    private String name;
    private int userId;
    private int chatId;
    /*---------------------------------------------------------------------------------------------*/
    public GroupChat(String name, int userId, int chatId){

        this.name = name;
        this.chatId = chatId;
        this.userId = userId;

    }
    /*---------------------------------------------------------------------------------------------*/
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    /*---------------------------------------------------------------------------------------------*/

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    /*---------------------------------------------------------------------------------------------*/

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getChatId() {
        return chatId;
    }
    /*---------------------------------------------------------------------------------------------*/



}