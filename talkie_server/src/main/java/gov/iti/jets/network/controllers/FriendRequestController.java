package gov.iti.jets.network.controllers;

import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FriendRequestController extends Remote {


    String sendFriendRequest (User user, User friend) throws RemoteException;

    String acceptFriendRequest (User user, User friend) throws RemoteException;

    String refuseFriendRequest (User user, User friend) throws RemoteException;
    List<FriendRequest> findAllFriendRequests(User receiver) throws RemoteException;

    String cancelRequest(User user, User friend) throws RemoteException;


}
