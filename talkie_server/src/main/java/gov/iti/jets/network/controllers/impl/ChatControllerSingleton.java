package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.AddUserToGroupChatRequest;
import gov.iti.jets.dto.requests.CreateGroupChatRequest;
import gov.iti.jets.dto.requests.GetChatsRequest;
import gov.iti.jets.dto.responses.AddUserToGroupChatResponse;
import gov.iti.jets.dto.responses.CreateGroupChatResponse;
import gov.iti.jets.dto.responses.GetChatsResponse;
import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.mappers.GroupChatMapper;
import gov.iti.jets.mappers.UserGroupMapper;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.ChatController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.persistence.GroupChatDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.services.ChatService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class ChatControllerSingleton extends UnicastRemoteObject implements ChatController {

    private static ChatControllerSingleton instance;
    private ChatControllerSingleton() throws RemoteException {}
    public static ChatControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new ChatControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("ChatController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public GetChatsResponse getAllChat(GetChatsRequest request) {
        ChatService chatService = new ChatService();
        return new GetChatsResponse(chatService.getAllRegularChats(request.getPhoneNumber()),
                chatService.getAllGroupChats(request.getPhoneNumber()));
    }

    @Override
    public CreateGroupChatResponse createGroupChat(CreateGroupChatRequest request) throws RemoteException {
        ChatService chatService = new ChatService();
        String phoneNumber = request.getUserPhoneNumber();
        String groupName = request.getGroupName();
        Optional<GroupChat> groupChatOptional = chatService.createGroupChat(phoneNumber, groupName);
        if(groupChatOptional.isPresent()){
            return new CreateGroupChatResponse(groupChatOptional.get());
        }
        return new CreateGroupChatResponse(null);
    }

    public AddUserToGroupChatResponse addUserToGroupChat(AddUserToGroupChatRequest request) throws RemoteException{
        UserGroupMapper userGroupMapper = new UserGroupMapper();
        GroupChatDao groupChatDao = new GroupChatDao();
        UserDao userDao = new UserDao();
        UserMapper userMapper = new UserMapper();
        GroupChatMapper groupChatMapper = new GroupChatMapper();
        String phoneNumber = request.getUserPhoneToBeAdded();
        String chatId = request.getChatId();
        GroupChatEntity groupChatEntity = groupChatDao.findGroupChatByChatId(chatId).get();
        List<GroupChat> chats = groupChatMapper.findAllGroupChatsByOwnerId(userDao.findUserById(groupChatEntity.getOwnerId()).get().getPhoneNumber());
        GroupChat groupChat = chats.stream().filter(chat -> chat.getChatId().equals(chatId)).findFirst().get();
        userGroupMapper.addUserToGroupChat(phoneNumber, chatId);
        CallbackController cb = OnlineStatusControllerSingleton.getUsers().get(phoneNumber);
        if(cb != null){
            cb.addCurrentUserToGroupChat(groupChat);
        }
        return new AddUserToGroupChatResponse(userMapper.entityToModel(userMapper.getUserByPhoneNumber(phoneNumber).get()), null);
    }
}
