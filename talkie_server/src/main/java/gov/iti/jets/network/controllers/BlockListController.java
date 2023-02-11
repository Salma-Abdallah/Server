package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.BlockListRequest;
import gov.iti.jets.dto.responses.BlockListResponse;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BlockListController extends Remote {

    BlockListResponse addBlockUser(BlockListRequest request);
    BlockListResponse removeBlockUser(BlockListRequest request);
    BlockListResponse findAllBlockedUsersByUserId(BlockListRequest request);
    BlockListResponse findAllBlockersByBlockedUserId(BlockListRequest request);


}
