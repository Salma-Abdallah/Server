package gov.iti.jets.services;

import gov.iti.jets.mappers.MessageMapper;
import gov.iti.jets.models.Message;

import java.util.List;

public class MessageService {

    private MessageMapper messageMapper;

    public MessageService() {
        this.messageMapper = new MessageMapper();
    }

    public List<Message> findMessagesByChatId(String chatId){
       return messageMapper.findMessagesByChatId(chatId);
    }
}
