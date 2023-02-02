package gov.iti.jets.models;
public class Regular_Chat {




    private int id;
    private int firstParticipantId;
    private int secondParticipantId;

    public Regular_Chat( int firstParticipantId, int secondParticipantId, int id){


        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId= secondParticipantId;
        this.id = id;
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
}