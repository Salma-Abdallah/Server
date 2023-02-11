package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.BlockListRequest;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BlockListController extends Remote {
    BlockList addBlockedUser(BlockList blockList) throws RemoteException;
    int removeBlockedUser(BlockList blockList) throws RemoteException;

    List<User> findAllBlockedUsersByUserId(Integer userId) throws RemoteException;

    List<User> findAllBlockersByBlockedUserId(Integer userId) throws RemoteException;

}
