package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.network.controllers.AdminMessageController;
import gov.iti.jets.network.controllers.CallbackController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminMessageControllerSingleton extends UnicastRemoteObject implements AdminMessageController {
    protected AdminMessageControllerSingleton() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(String message){
        Map<String, CallbackController> callbackUsers = OnlineStatusControllerSingleton.getInstance().getUsers();
        if(callbackUsers != null){
            callbackUsers.forEach((key, value)->{
//                value.receiveNewMessage(message);
            });
        }
    }
}
