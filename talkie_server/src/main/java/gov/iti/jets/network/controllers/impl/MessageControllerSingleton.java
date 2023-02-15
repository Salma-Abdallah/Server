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
        System.out.println(request.getMessage());
        Optional<Message> messageOptional = sendMessageToRegularChat(messageService, chatService, request);
        if(messageOptional.isPresent()){
            return new SendMessageResponse(messageOptional.get());
        }
        messageOptional = sendMessageToGroupChat(messageService, chatService, request);
        if(messageOptional.isPresent()){
            return new SendMessageResponse(messageOptional.get());
        }
        return new SendMessageResponse(null);
    }

    public SendMessageResponse doShit(SendMessageRequest request) throws RemoteException {
        MessageService messageService = new MessageService();
        ChatService chatService = new ChatService();
        System.out.println(request.getMessage());
        Optional<Message> messageOptional = sendMessageToRegularChat(messageService, chatService, request);
        if(messageOptional.isPresent()){
            return new SendMessageResponse(messageOptional.get());
        }
        messageOptional = sendMessageToGroupChat(messageService, chatService, request);
        if(messageOptional.isPresent()){
            return new SendMessageResponse(messageOptional.get());
        }
        return new SendMessageResponse(null);
    }

    private Optional<Message> sendMessageToRegularChat(MessageService messageService, ChatService chatService, SendMessageRequest request) throws RemoteException {
        List<RegularChat> regularChats = chatService.getAllRegularChats(request.getMessage().getAuthor().getPhoneNumber());
        for(RegularChat regularChat : regularChats){
            if(regularChat.getChatId().equals(request.getMessage().getChatId())){
                if(!messageService.isReceiverBlocked(request.getMessage().getAuthor().getPhoneNumber(), regularChat.getFirstParticipant().getPhoneNumber())
                        && !messageService.isSenderBlocked(request.getMessage().getAuthor().getPhoneNumber(), regularChat.getFirstParticipant().getPhoneNumber())){
                    Optional<Message> messageOptional = messageService.insert(request.getMessage());
                    CallbackController callbackController = OnlineStatusControllerSingleton.getInstance().getUsers()
                            .get(regularChat.getFirstParticipant().getPhoneNumber());
                    if(callbackController != null){
                        callbackController.receiveNewMessage(request.getMessage());
                    }
                    return messageOptional;
                }
            }
        }
        return Optional.empty();
    }
    private Optional<Message> sendMessageToGroupChat(MessageService messageService, ChatService chatService, SendMessageRequest request) throws RemoteException {
        User author = request.getMessage().getAuthor();
        List<GroupChat> groupChats = chatService.getAllGroupChats(author.getPhoneNumber());
        for(GroupChat groupChat : groupChats){
            if(groupChat.getChatId().equals(request.getMessage().getChatId())){
                Optional<Message> messageOptional = messageService.insert(request.getMessage());
                for(User user : groupChat.getParticipants()){
                    CallbackController callbackController = OnlineStatusControllerSingleton.getInstance()
                            .getUsers().get(user.getPhoneNumber());
                    if(callbackController != null){
                        callbackController.receiveNewMessage(request.getMessage());
                    }
                    return messageOptional;
                }
                if(groupChat.getOwner().getPhoneNumber().equals(request.getMessage().getAuthor().getPhoneNumber())){
                    return messageOptional;
                }
            }
        }
        return Optional.empty();
    }
}