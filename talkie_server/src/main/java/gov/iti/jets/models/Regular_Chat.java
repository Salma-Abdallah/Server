package gov.iti.jets.models;
public class Regular_Chat {

    private User user;
    private Chat chat;



    private int id;
    private int firstParticipantId;
    private int secondParticipantId;

    public Regular_Chat( int firstParticipantId, int secondParticipantId){


        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId= secondParticipantId;
    }



    public void setFirstParticipantId(int firstParticipantId) {
        this.firstParticipantId = firstParticipantId;
    }
    public int getFirstParticipantId() {
        return firstParticipantId;
    }




    public void setSecondParticipantId(int secondParticipantId) {
        this.secondParticipantId = secondParticipantId;
    }
    public int getSecondParticipantId() {
        return secondParticipantId;
    }



    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public void setChat(Chat chat) {
        this.chat=chat;
    }
    public Chat getChat() {
        return chat;
    }



    public void setuser(User user) {
        this.user=user;
    }
    public User getUser() {
        return user;
    }
}