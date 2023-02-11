package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.BlockListRequest;
import gov.iti.jets.dto.responses.BlockListResponse;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.BlockListController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.BlockListServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BlockListControllerSingleton extends UnicastRemoteObject implements BlockListController {
    private static BlockListControllerSingleton instance;
    BlockListServices blockListServices = new BlockListServices();

    protected BlockListControllerSingleton() throws RemoteException {
    }

    public static BlockListControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new BlockListControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("BlockListController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public BlockListResponse addBlockUser(BlockListRequest request) {
        return new BlockListResponse(new BlockListServices().insert(request.getUserPhoneNumber(),request.getBlockedUserPhoneNumber()));
    }

    @Override
    public BlockListResponse removeBlockUser(BlockListRequest request) {
        return new BlockListResponse(new BlockListServices().delete(request.getUserPhoneNumber(),request.getBlockedUserPhoneNumber()));
    }

    @Override
    public BlockListResponse findAllBlockedUsersByUserId(BlockListRequest request) {
        return new BlockListResponse(new BlockListServices().findAllBlockedUsersByUserId(request.getUserPhoneNumber()));
    }

    @Override
    public BlockListResponse findAllBlockersByBlockedUserId(BlockListRequest request) {
        return new BlockListResponse(new BlockListServices().findAllBlockersByBlockedUserId(request.getUserPhoneNumber()));
    }


}
