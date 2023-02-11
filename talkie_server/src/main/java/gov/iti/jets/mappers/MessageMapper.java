package gov.iti.jets.mappers;

import gov.iti.jets.entities.MessageEntity;
import gov.iti.jets.models.Message;
import gov.iti.jets.persistence.ChatDao;
import gov.iti.jets.persistence.MessageDao;
import gov.iti.jets.persistence.UserDao;
import java.util.List;

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
            new Message(userMapper.entityToModel(messageEntity.getAuthor()),
                    chatId, messageEntity.getFontStyle(), messageEntity.getFontColor(), messageEntity.getFontSize(),
                    messageEntity.isBold(), messageEntity.isItalic(), messageEntity.isUnderlined(), messageEntity.getTextBackground(),
                    messageEntity.getSentAt(), messageEntity.getContent(), messageEntity.getFileUrl())
        ).toList();
    }
}
