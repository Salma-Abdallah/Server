package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.AddBlockedUserRequest;
import gov.iti.jets.dto.requests.RemoveBlockedUserRequest;
import gov.iti.jets.dto.requests.ViewBlockListRequest;
import gov.iti.jets.dto.responses.AddBlockedUserResponse;
import gov.iti.jets.dto.responses.RemovedBlockedUserResponse;
import gov.iti.jets.dto.responses.ViewBlockListResponse;
import gov.iti.jets.network.controllers.BlockListController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.BlockListServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BlockListControllerSingleton extends UnicastRemoteObject implements BlockListController {
    private static BlockListControllerSingleton instance;

    protected BlockListControllerSingleton() throws RemoteException {
        super();
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
    public AddBlockedUserResponse addBlockUser(AddBlockedUserRequest  request) {
        //return new AddBlockedUserResponse(new BlockListServices().insert(request.getUserPhoneNumber(),request.getBlockedUserPhoneNumber()));
        AddBlockedUserResponse addBlockedUserResponse=new AddBlockedUserResponse(new BlockListServices().insert(request.getUserPhoneNumber(),request.getBlockedUserPhoneNumber()));

        return addBlockedUserResponse;

    }

    @Override
    public RemovedBlockedUserResponse removeBlockUser(RemoveBlockedUserRequest request) {
        return new RemovedBlockedUserResponse(new BlockListServices().delete(request.getUserPhoneNumber(), request.getBlockedUserPhoneNumber()));
    }

    @Override
    public ViewBlockListResponse findAllBlockedUsersByUserId(ViewBlockListRequest request) {
        return new ViewBlockListResponse(new BlockListServices().findAllBlockedUsersByUserPhoneNumber(request.getUserPhoneNumber()));
    }


}
