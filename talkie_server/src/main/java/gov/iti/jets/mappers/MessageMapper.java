package gov.iti.jets.mappers;

import gov.iti.jets.entities.MessageEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.Message;
import gov.iti.jets.persistence.ChatDao;
import gov.iti.jets.persistence.MessageDao;
import gov.iti.jets.persistence.UserDao;
import java.util.List;
import java.util.Optional;

public class MessageMapper {

    private ChatDao chatDao;
    private MessageDao messageDao;
    private UserDao userDao;
    private UserMapper userMapper;

    public MessageMapper() {
        chatDao = new ChatDao();
        messageDao = new MessageDao();
        userDao = new UserDao();
        userMapper = new UserMapper();
    }

    public List<Message> findMessagesByChatId(String chatId){
        List<MessageEntity> messageEntities = messageDao.findMessagesByChatID(chatId);
        return messageEntities.stream().map((messageEntity) ->
            new Message(messageEntity.getId(), userMapper.entityToModel(messageEntity.getAuthor()),
                    chatId, messageEntity.getFontStyle(), messageEntity.getFontColor(), messageEntity.getFontSize(),
                    messageEntity.isBold(), messageEntity.isItalic(), messageEntity.isUnderlined(), messageEntity.getTextBackground(),
                    messageEntity.getSentAt(), messageEntity.getContent(), messageEntity.getFileUrl(), messageEntity.isSeen())
        ).toList();
    }
    public Optional<Message> insert(Message message){
        UserEntity author = userMapper.getUserByPhoneNumber(message.getAuthor().getPhoneNumber()).get();
        Optional<MessageEntity> messageEntityOptional = messageDao.saveMessage(new MessageEntity(author, message.getChatId(),
                                                message.getFontStyle(), message.getFontColor(), message.getFontSize(),
                                                message.isBold(), message.isItalic(), message.isUnderlined(),
                                                message.getTextBackground(), message.getSentAt(), message.getContent(),
                                                message.getFileUrl(), message.isSeen()));
        if(messageEntityOptional.isPresent()){
            message.setId(messageEntityOptional.get().getId());
            return Optional.of(message);
        }
        return Optional.empty();
    }
    public int updateMessageStatusByPhoneNumberAndChatId(String phoneNumber, String chatId){
        return messageDao.updateMessageStatusByPhoneNumberAndChatId(phoneNumber, chatId);
    }
}
