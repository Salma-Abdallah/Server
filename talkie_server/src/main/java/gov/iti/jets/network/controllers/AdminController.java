package gov.iti.jets.network.controllers;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.models.Admin;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminController extends Remote {
    Admin register(AdminEntity adminEntity);
    void sendMessage(MessageController messageController) throws RemoteException;
}
