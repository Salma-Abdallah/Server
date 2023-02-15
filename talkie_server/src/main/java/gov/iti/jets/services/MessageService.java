package gov.iti.jets.services;

import gov.iti.jets.mappers.BlockListMapper;
import gov.iti.jets.mappers.MessageMapper;
import gov.iti.jets.models.Message;
import gov.iti.jets.models.User;

import java.util.List;
import java.util.Optional;

public class MessageService {

    private MessageMapper messageMapper;
    private BlockListMapper blockListMapper;

    public MessageService() {
        this.messageMapper = new MessageMapper();
        this.blockListMapper = new BlockListMapper();
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
    public boolean isSenderBlocked(String senderPhoneNumber, String receiverPhoneNumber){
        return blockListMapper.isUserBlocked(senderPhoneNumber, receiverPhoneNumber);
    }
    public boolean isReceiverBlocked(String senderPhoneNumber, String receiverPhoneNumber){
        return blockListMapper.isUserBlocked(receiverPhoneNumber, senderPhoneNumber);
    }
}
