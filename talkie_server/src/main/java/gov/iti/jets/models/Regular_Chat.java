package gov.iti.jets.models;
public class Regular_Chat {

    private User user;
    private Chat chat;



    private int id;
    private int first_participant_id;
    private int second_participant_id;

    public Regular_Chat( int first_participant_id, int second_participant_id){

        user.getid();
        chat.getid();
        this.first_participant_id = first_participant_id;
        this.second_participant_id = second_participant_id;
    }



    public void setFirst_participant_id(int first_participant_id) {
        this.first_participant_id = first_participant_id;
    }
    public int getFirst_participant_id() {
        return first_participant_id;
    }




    public void setSecond_participant_id(int secondParticipantId) {
        this.second_participant_id = secondParticipantId;
    }
    public int getSecond_participant_id() {
        return second_participant_id;
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