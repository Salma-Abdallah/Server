package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.dto.requests.ChangeUserStatusRequest;
import gov.iti.jets.dto.responses.ChangeUserStatusResponse;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatService;
import gov.iti.jets.services.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OnlineStatusControllerSingleton extends UnicastRemoteObject implements OnlineStatusController {

    private static OnlineStatusControllerSingleton instance;
    private static ConcurrentMap<String, CallbackController> users = new ConcurrentHashMap<>();
    ChatService chatService = new ChatService();
    UserService userService = new UserService();
    private OnlineStatusControllerSingleton() throws RemoteException {}
    public static OnlineStatusControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new OnlineStatusControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("OnlineStatusController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Map<String, CallbackController> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    @Override
    public void connect(User user, CallbackController callbackController) throws RemoteException {
        userService.updateStatusByUserPhoneNumber(user.getPhoneNumber(), "Available");
        updateStatusCallback(user.getPhoneNumber(), "Available");
        users.put(user.getPhoneNumber(), callbackController);
    }

    @Override
    public void disconnect(String phoneNumber) throws RemoteException {
        userService.updateStatusByUserPhoneNumber(phoneNumber, "Offline");
        updateStatusCallback(phoneNumber, "Offline");
        users.remove(phoneNumber);
    }

    @Override
    public void ping() throws RemoteException {
        new Thread(()->{
            while (true){
                users.forEach((key, value)->{
                    try {
                        System.out.println(key);
                        value.respond();
                    } catch (RemoteException e) {
                        System.out.println("[INFO] Connection: " + "["+key+"]" + "disconnected.");
                        try {
                            disconnect(key);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public ChangeUserStatusResponse changeStatus(ChangeUserStatusRequest request) throws RemoteException {
        String phoneNumber = request.getCurrentUserPhoneNumber();
        String status = request.getStatus();
        userService.updateStatusByUserPhoneNumber(phoneNumber, status);
        updateStatusCallback(phoneNumber, status);
        return new ChangeUserStatusResponse(true);
    }

    private void updateStatusCallback(String phoneNumber, String status) throws RemoteException {
        for(RegularChat chat : chatService.getAllRegularChats(phoneNumber)){
            CallbackController cb = users.get(chat.getFirstParticipant().getPhoneNumber());
            if(cb != null){
                cb.friendOnlineStatus(chat.getChatId(), status);
            }
        }
    }
}
