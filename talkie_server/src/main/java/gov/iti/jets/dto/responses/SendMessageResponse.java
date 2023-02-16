package gov.iti.jets.dto.responses;

import gov.iti.jets.models.Message;

import java.io.Serializable;

public class SendMessageResponse implements Serializable {
    private Message message;

    public SendMessageResponse(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendMessageResponse{" +
                "message=" + message +
                '}';
    }
}
