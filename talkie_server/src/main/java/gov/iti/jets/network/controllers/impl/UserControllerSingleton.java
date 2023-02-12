package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.CallBackController;
import gov.iti.jets.network.controllers.UserController;
import gov.iti.jets.network.manager.NetworkManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class UserControllerSingleton extends UnicastRemoteObject implements UserController {

    private static UserControllerSingleton instance;
    private HashMap<String, User> users = new HashMap<>();
    private UserControllerSingleton() throws RemoteException {}

    public static UserControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new UserControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("UserController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public User register(User user) throws RemoteException {
        System.out.println(user);
        return user;
    }

    @Override
    public User edit(User user) throws RemoteException {
        return user;
    }

    @Override
    public void sendMessage(CallBackController callBackController) throws RemoteException {
        callBackController.send();
        callBackController.receive();
    }
}
