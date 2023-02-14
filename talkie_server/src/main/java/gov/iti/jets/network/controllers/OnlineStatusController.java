package gov.iti.jets.network.controllers;


import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OnlineStatusController extends Remote {

    void connect(User user, CallbackController callbackController) throws RemoteException;

    void disconnect(User user) throws RemoteException;

    void ping() throws RemoteException;
}
