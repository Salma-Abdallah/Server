package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.network.controllers.AdminMessageController;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.manager.NetworkManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminMessageControllerSingleton extends UnicastRemoteObject implements AdminMessageController {
    private static AdminMessageControllerSingleton instance;
    private AdminMessageControllerSingleton() throws RemoteException {}
    public static AdminMessageControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new AdminMessageControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("AuthenticationController", instance);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public void sendMessage(String message){
        Map<String, CallbackController> callbackUsers = OnlineStatusControllerSingleton.getInstance().getUsers();
        callbackUsers.forEach((key, value)->{
            CallbackController cb = OnlineStatusControllerSingleton.getUsers().get(key);
            if(cb != null){
                try {
                    cb.broadcastNotification(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
