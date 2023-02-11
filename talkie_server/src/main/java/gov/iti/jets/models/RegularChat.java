package gov.iti.jets.models;
public class RegularChat extends Chat{
    private static final long serialVersionUID = 5887637195618767821L;

    private User firstParticipantId;
    private User secondParticipantId;

    public RegularChat(String chatId, User firstParticipantId, User secondParticipantId) {
        this.chatId = chatId;
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;
    }

    public RegularChat(String chatId, User firstParticipantId) {
        this.chatId = chatId;
        this.firstParticipantId = firstParticipantId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setFirstParticipantId(User firstParticipantId) {
        this.firstParticipantId = firstParticipantId;
    }

    public void setSecondParticipantId(User secondParticipantId) {
        this.secondParticipantId = secondParticipantId;
    }

    public User getFirstParticipantId() {
        return firstParticipantId;
    }

    public User getSecondParticipantId() {
        return secondParticipantId;
    }

    @Override
    public String toString() {
        return "RegularChat{" +
                "chatId='" + chatId + '\'' +
                ", firstParticipantId=" + firstParticipantId +
                ", secondParticipantId=" + secondParticipantId +
                '}';
    }
}