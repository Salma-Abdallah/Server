package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.dto.requests.AcceptFriendRequest;
import gov.iti.jets.dto.requests.CancelFriendRequest;
import gov.iti.jets.dto.requests.LoadFriendReqRequest;
import gov.iti.jets.dto.requests.RefuseFriendRequest;
import gov.iti.jets.dto.responses.*;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.FriendRequestController;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.FriendRequestServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class FriendRequestControllerSingleton extends UnicastRemoteObject implements FriendRequestController {

    private static FriendRequestControllerSingleton instance;
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

    private FriendRequestControllerSingleton() throws RemoteException {}


    public CancelFriendRequestResponse cancel(CancelFriendRequest cancelFriendRequest) {
        return new CancelFriendRequestResponse(new FriendRequestServices().cancel(cancelFriendRequest.getUserPhoneNumber(),cancelFriendRequest.getFriendPhoneNumber()));
    }


    public RefuseFriendFriendResponse refuse(RefuseFriendRequest refuseFriendRequest) {
        return new RefuseFriendFriendResponse(new FriendRequestServices().refuse(refuseFriendRequest.getUserPhoneNumber(),refuseFriendRequest.getFriendPhoneNumber()));
    }

    public AcceptFriendResponse accept(AcceptFriendRequest acceptFriendRequest) {
        return new AcceptFriendResponse(new FriendRequestServices().accept(acceptFriendRequest.getUserPhoneNumber(), acceptFriendRequest.getFriendPhoneNumber()));
    }

    public LoadFriendReqResponse getSentFriendRequestByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getSentFriendRequestByUsePhoneNumber(loadFriendReqRequest.getUserPhoneNumber()));
    }

    @Override
    public LoadFriendReqResponse getReceivedFriendReqByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getReceivedFriendReqByUserPhoneNumber(loadFriendReqRequest.getUserPhoneNumber()));
    }
}
