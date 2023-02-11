package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.dto.requests.FriendReqRequest;
import gov.iti.jets.dto.responses.ContactResponse;
import gov.iti.jets.dto.responses.FriendReqResponse;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.FriendRequestController;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ContactServices;
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
    public FriendReqResponse cancel(FriendReqRequest friendReqRequest) {
        return new FriendReqResponse(new FriendResquestServices().cancel(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));

    }

    @Override
    public FriendReqResponse refuse(FriendReqRequest friendReqRequest) {
        return new FriendReqResponse(new FriendResquestServices().refuse(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));

    }

    @Override
    public FriendReqResponse save(FriendReqRequest friendReqRequest) {
        return new FriendReqResponse(new FriendResquestServices().save(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));

    }

    @Override
    public FriendReqResponse getSentFriendRequestByUserID (FriendReqRequest friendReqRequest) {
        return new FriendReqResponse(new FriendResquestServices().getSentFriendRequestByUserID(friendReqRequest.getUserPhoneNumber()));

    }

    @Override
    public FriendReqResponse getReceivedFriendReqByUserID (FriendReqRequest friendReqRequest) {
        return new FriendReqResponse(new FriendResquestServices().getReceivedFriendReqByUserID(friendReqRequest.getUserPhoneNumber()));

    }
}
