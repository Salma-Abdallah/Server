package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.GetChatsRequest;
import gov.iti.jets.dto.responses.GetChatsResponse;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.models.Chat;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.ChatController;
import gov.iti.jets.network.controllers.MessageController;
import gov.iti.jets.network.controllers.UserController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatService;
import gov.iti.jets.services.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

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
        return new GetChatsResponse(new ChatService().getAllChats(request.getPhoneNumber()));
    }
}
