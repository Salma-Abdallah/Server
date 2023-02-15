package gov.iti.jets.models;
public class RegularChat extends Chat{
    private static final long serialVersionUID = 5887637195618767821L;

    private User firstParticipant;
    private User secondParticipant;

    public RegularChat(String chatId, User firstParticipant, User secondParticipant) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
    }

    public RegularChat(String chatId, User firstParticipant) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipant;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setFirstParticipantId(User firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public void setSecondParticipantId(User secondParticipant) {
        this.secondParticipant = secondParticipant;
    }

    public User getFirstParticipant() {
        return firstParticipant;
    }
    public void setFirstParticipant(User firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public User getSecondParticipant() {
        return secondParticipant;
    }

    public void setSecondParticipant(User secondParticipant) {
        this.secondParticipant = secondParticipant;
    }

    @Override
    public String toString() {
        return "RegularChat{" +
                "firstParticipant=" + firstParticipant +
                ", secondParticipant=" + secondParticipant +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}