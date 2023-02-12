package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.GetChatsRequest;
import gov.iti.jets.dto.requests.GetMessagesRequest;
import gov.iti.jets.dto.responses.GetChatsResponse;
import gov.iti.jets.dto.responses.GetMessagesResponse;
import gov.iti.jets.network.controllers.ChatController;
import gov.iti.jets.network.controllers.MessageController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatService;
import gov.iti.jets.services.MessageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageControllerSingleton extends UnicastRemoteObject implements MessageController {

    private static MessageControllerSingleton instance;
    private MessageControllerSingleton() throws RemoteException {}
    public static MessageControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new MessageControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("MessageController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public GetMessagesResponse getAllMessages(GetMessagesRequest request) {
        MessageService messageService = new MessageService();
        return new GetMessagesResponse(messageService.findMessagesByChatId(request.getChatId()));
    }
}