package gov.iti.jets.entities;
public class RegularChatEntity {
    private String id;
    private Integer firstParticipantId;
    private Integer secondParticipantId;

    public RegularChatEntity(String id, Integer firstParticipantId, Integer secondParticipantId) {
        this.id = id;
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFirstParticipantId() {
        return firstParticipantId;
    }

    public void setFirstParticipantId(Integer firstParticipantId) {
        this.firstParticipantId = firstParticipantId;
    }

    public Integer getSecondParticipantId() {
        return secondParticipantId;
    }

    public void setSecondParticipantId(Integer secondParticipantId) {
        this.secondParticipantId = secondParticipantId;
    }
}