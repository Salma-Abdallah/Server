package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.FriendReqRequest;
import gov.iti.jets.dto.responses.FriendReqResponse;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FriendRequestController extends Remote {


    FriendReqResponse cancel (FriendReqRequest friendReqRequest);
    FriendReqResponse refuse (FriendReqRequest friendReqRequest);
    FriendReqResponse save(FriendReqRequest friendReqRequest);
    FriendReqResponse getSentFriendRequestByUserID (FriendReqRequest friendReqRequest);
    FriendReqResponse getReceivedFriendReqByUserID(FriendReqRequest friendReqRequest);




}
