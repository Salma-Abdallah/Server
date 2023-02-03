package gov.iti.jets.models;

public class GroupChat {
    private String chatId;
    private int ownerId;
    private String name;

    public GroupChat(String chatId, int ownerId, String name) {
        this.chatId = chatId;
        this.ownerId = ownerId;
        this.name = name;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}