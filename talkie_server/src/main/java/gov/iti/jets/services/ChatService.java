package gov.iti.jets.services;

import gov.iti.jets.mappers.GroupChatMapper;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.mappers.UserGroupMapper;
import gov.iti.jets.models.Chat;
import gov.iti.jets.models.RegularChat;

import java.util.ArrayList;
import java.util.List;

public class ChatService {
    private RegularChatMapper regularChatMapper;
    private GroupChatMapper groupChatMapper;
    private UserGroupMapper userGroupMapper;

    public ChatService() {
        regularChatMapper = new RegularChatMapper();
        groupChatMapper = new GroupChatMapper();
        userGroupMapper = new UserGroupMapper();
    }

    public List<Chat> getAllChats(String phoneNumber){
        List<Chat> chats = new ArrayList<>();
        chats.addAll(regularChatMapper.findAllRegularChatsByPhoneNumber(phoneNumber));
        chats.addAll(groupChatMapper.findAllGroupChatsByOwnerId(phoneNumber));
        chats.addAll(userGroupMapper.findAllGroupChatsByPhoneNumber(phoneNumber));
        return chats;
    }
}
