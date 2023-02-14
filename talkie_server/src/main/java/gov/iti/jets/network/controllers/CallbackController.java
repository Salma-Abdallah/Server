package gov.iti.jets.network.controllers;

import gov.iti.jets.models.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackController extends Remote {

    void respond() throws RemoteException;
    void receiveNewMessage(Message message) throws RemoteException;

}
