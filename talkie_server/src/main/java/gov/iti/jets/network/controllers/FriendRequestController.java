package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.AcceptFriendRequest;
import gov.iti.jets.dto.requests.CancelFriendRequest;
import gov.iti.jets.dto.requests.LoadFriendReqRequest;
import gov.iti.jets.dto.requests.RefuseFriendRequest;
import gov.iti.jets.dto.responses.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FriendRequestController extends Remote {


   CancelFriendRequestResponse cancel (CancelFriendRequest cancelFriendRequest) throws RemoteException;
   RefuseFriendFriendResponse refuse (RefuseFriendRequest refuseFriendRequest) throws RemoteException;
   AcceptFriendResponse accept (AcceptFriendRequest acceptFriendRequest) throws  RemoteException;
   LoadFriendReqResponse getSentFriendRequestByUserID (LoadFriendReqRequest loadFriendReqRequest)throws RemoteException;
   LoadFriendReqResponse getReceivedFriendReqByUserID(LoadFriendReqRequest loadFriendReqRequest) throws RemoteException;




}
