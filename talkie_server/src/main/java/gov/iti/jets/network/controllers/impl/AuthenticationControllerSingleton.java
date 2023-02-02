package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.AuthenticationController;
import gov.iti.jets.network.manager.NetworkManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationControllerSingleton extends UnicastRemoteObject implements AuthenticationController {

    private static AuthenticationControllerSingleton instance;
    private AuthenticationControllerSingleton() throws RemoteException {

    }
    public static AuthenticationControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new AuthenticationControllerSingleton();
                NetworkManager.getRegistry().rebind("AuthenticationController", instance);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    @Override
    public User authenticate(User user) throws RemoteException {
        return user;
    }
}
