package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.FriendRequestController;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.FriendResquestServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class FriendRequestControllerSingleton extends UnicastRemoteObject implements FriendRequestController {

    private static FriendRequestControllerSingleton instance;

    private FriendResquestServices friendResquestServices;
    private FriendRequest friendRequest;
    private User user = new User();
    private User friend = new User();


    public static FriendRequestControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new FriendRequestControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("FriendRequestController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    protected FriendRequestControllerSingleton() throws RemoteException {
        super();
    }
    @Override
    public String sendFriendRequest(User user, User friend) throws RemoteException {
        friendResquestServices.sendFriendRequest(user, friend);

        return "Send Friend Request";
    }

    @Override
    public String acceptFriendRequest(User user, User friend) throws RemoteException {
        friendResquestServices.acceptFriendRequest(user, friend);

        return "Accepted Friend Request";
    }

    @Override
    public String refuseFriendRequest(User user, User friend) throws RemoteException {
        friendResquestServices.refuseFriendRequest(user, friend);

        return "refused Friend Request";
    }

    @Override
    public List<FriendRequest> findAllFriendRequests(User receiver) throws RemoteException {
        return friendResquestServices.findAllFriendRequests(receiver);

    }

    @Override
    public String cancelRequest(User user, User friend) throws RemoteException {
        friendResquestServices.cancelRequest(user, friend);
        return "Canceled Successfully";
    }
}
