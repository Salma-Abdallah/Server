package gov.iti.jets.services;

import gov.iti.jets.mappers.MessageMapper;
import gov.iti.jets.models.Message;

import java.util.List;
import java.util.Optional;

public class MessageService {

    private MessageMapper messageMapper;

    public MessageService() {
        this.messageMapper = new MessageMapper();
    }

    public List<Message> findMessagesByChatId(String chatId){
       return messageMapper.findMessagesByChatId(chatId);
    }
    public Optional<Message> insert(Message message){
        return messageMapper.insert(message);
    }
    public int updateMessageStatusByPhoneNumberAndChatId(String phoneNumber, String chatId){
        return messageMapper.updateMessageStatusByPhoneNumberAndChatId(phoneNumber, chatId);
    }
}
