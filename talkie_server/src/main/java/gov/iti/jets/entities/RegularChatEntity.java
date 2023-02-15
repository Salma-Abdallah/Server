package gov.iti.jets.entities;

import gov.iti.jets.models.RegularChat;

import java.io.Serializable;

public class RegularChatEntity implements Serializable {
    private String id;
    private Integer firstParticipantId;
    private Integer secondParticipantId;

    public RegularChatEntity(String id, Integer firstParticipantId, Integer secondParticipantId) {
        this.id = id;
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;

    }
    public RegularChatEntity(Integer firstParticipantId, Integer secondParticipantId) {
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

    @Override
    public String toString() {
        return "RegularChatEntity{" +
                "id='" + id + '\'' +
                ", firstParticipantId=" + firstParticipantId +
                ", secondParticipantId=" + secondParticipantId +
                '}';
    }
}