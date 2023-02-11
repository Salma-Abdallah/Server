package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.BlockListController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.BlockListServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import static sun.awt.image.PixelConverter.UshortGray.instance;

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
            NetworkManager.getRegistry().rebind("UserController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    @Override
    public BlockList addBlockedUser(BlockList blockList) throws RemoteException {
        return blockListServices.save(blockList);
    }

    @Override
    public int removeBlockedUser(BlockList blockList) throws RemoteException {
        return blockListServices.delete(blockList);
    }

    @Override
    public List<User> findAllBlockedUsersByUserId(Integer userId) throws RemoteException {
        return blockListServices.findAllBlockedUsersByUserId(userId);
    }

    @Override
    public List<User> findAllBlockersByBlockedUserId(Integer userId) throws RemoteException {
        return blockListServices.findAllBlockersByBlockedUserId(userId);
    }


}
