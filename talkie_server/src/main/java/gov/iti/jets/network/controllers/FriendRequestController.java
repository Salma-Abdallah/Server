package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.LoadFriendReqRequest;
import gov.iti.jets.dto.responses.FriendReqResponse;
import gov.iti.jets.dto.responses.LoadFriendReqResponse;

import java.rmi.Remote;

public interface FriendRequestController extends Remote {


    FriendReqResponse cancel (LoadFriendReqRequest friendReqRequest);
    FriendReqResponse refuse (LoadFriendReqRequest friendReqRequest);
    FriendReqResponse save(LoadFriendReqRequest friendReqRequest);
    LoadFriendReqResponse getSentFriendRequestByUserID (LoadFriendReqRequest loadFriendReqRequest);
    LoadFriendReqResponse getReceivedFriendReqByUserID(LoadFriendReqRequest loadFriendReqRequest);




}
