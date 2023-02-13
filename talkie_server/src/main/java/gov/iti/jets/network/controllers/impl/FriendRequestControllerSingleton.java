package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.dto.requests.FriendReqRequest;
import gov.iti.jets.dto.requests.LoadFriendReqRequest;
import gov.iti.jets.dto.responses.FriendReqResponse;
import gov.iti.jets.dto.responses.LoadFriendReqResponse;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.FriendRequestController;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.FriendRequestServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class FriendRequestControllerSingleton extends UnicastRemoteObject implements FriendRequestController {

    private static FriendRequestControllerSingleton instance;

    private FriendRequestServices friendResquestServices;
    private FriendRequest friendRequest = new FriendRequest();
    private User user = new User();
    private User friend = new User();
    private LoadFriendReqRequest loadFriendReqRequest;




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

//    @Override
//    public FriendReqResponse cancel(FriendReqRequest friendReqRequest) {
//        return new FriendReqResponse(new FriendRequestServices().cancel(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));
//
//    }

//    @Override
//    public FriendReqResponse refuse(FriendReqRequest friendReqRequest) {
//        return new FriendReqResponse(new FriendRequestServices().refuse(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));
//
//    }


//    public FriendReqResponse save(FriendReqRequest friendReqRequest) {
//        return new FriendReqResponse(new FriendRequestServices().save(friendReqRequest.getUserPhoneNumber(), friendReqRequest.getFriendPhoneNumber()));
//
//    }

    @Override
    public FriendReqResponse cancel(FriendReqRequest friendReqRequest) {
        return null;
    }

    @Override
    public FriendReqResponse refuse(FriendReqRequest friendReqRequest) {
        return null;
    }

    @Override
    public FriendReqResponse save(FriendReqRequest friendReqRequest) {
        return null;
    }

    @Override
    public LoadFriendReqResponse getSentFriendRequestByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getSentFriendRequestByUserID(loadFriendReqRequest.getUserPhoneNumber()));

    }

    @Override
    public LoadFriendReqResponse getReceivedFriendReqByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getReceivedFriendReqByUserID(loadFriendReqRequest.getUserPhoneNumber()));

    }
}
