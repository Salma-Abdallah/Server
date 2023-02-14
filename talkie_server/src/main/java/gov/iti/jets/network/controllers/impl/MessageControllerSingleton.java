package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.GetMessagesRequest;
import gov.iti.jets.dto.requests.SendMessageRequest;
import gov.iti.jets.dto.responses.GetMessagesResponse;
import gov.iti.jets.dto.responses.SendMessageResponse;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.models.Message;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.MessageController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatService;
import gov.iti.jets.services.MessageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

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
    public GetMessagesResponse getAllMessages(GetMessagesRequest request){
        MessageService messageService = new MessageService();
        return new GetMessagesResponse(messageService.findMessagesByChatId(request.getChatId()));
    }

    @Override
    public SendMessageResponse sendMessage(SendMessageRequest request) throws RemoteException {
        MessageService messageService = new MessageService();
        ChatService chatService = new ChatService();
        Optional<Message> messageOptional = messageService.insert(request.getMessage());
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            List<RegularChat> regularChats = chatService.getAllRegularChats(message.getAuthor().getPhoneNumber());
            List<GroupChat> groupChats = chatService.getAllGroupChats(message.getAuthor().getPhoneNumber());
            for(RegularChat regularChat : regularChats){
                if(regularChat.getChatId().equals(message.getChatId())){
                    CallbackController callbackController = OnlineStatusControllerSingleton.getInstance().getUsers()
                            .get(regularChat.getFirstParticipantId().getPhoneNumber());
                    if(callbackController != null){
                        callbackController.receiveNewMessage(message);
                    }
                }
            }
            for(GroupChat groupChat : groupChats){
                if(groupChat.getChatId().equals(message.getChatId())){
                    for(User user : groupChat.getParticipants()){
                        CallbackController callbackController = OnlineStatusControllerSingleton.getInstance()
                                                                        .getUsers().get(user.getPhoneNumber());
                        if(callbackController != null){
                            callbackController.receiveNewMessage(message);
                        }
                    }
                }
            }
            return new SendMessageResponse(message);
        }
        return new SendMessageResponse(null);
    }
}