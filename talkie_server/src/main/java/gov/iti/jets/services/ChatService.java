package gov.iti.jets.services;

import gov.iti.jets.mappers.GroupChatMapper;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.mappers.UserGroupMapper;
import gov.iti.jets.models.Chat;
import gov.iti.jets.models.GroupChat;
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

    public List<RegularChat> getAllRegularChats(String phoneNumber){
        List<RegularChat> chats = new ArrayList<>();
        chats.addAll(regularChatMapper.findAllRegularChatsByPhoneNumber(phoneNumber));
        return chats;
    }
    public List<GroupChat> getAllGroupChats(String phoneNumber){
        List<GroupChat> chats = new ArrayList<>();
        chats.addAll(groupChatMapper.findAllGroupChatsByOwnerId(phoneNumber));
        chats.addAll(userGroupMapper.findAllGroupChatsByPhoneNumber(phoneNumber));
        return chats;
    }
}
