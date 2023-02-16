package gov.iti.jets.network.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminMessageController extends Remote {
    void sendMessage(String msg) throws RemoteException;

}
