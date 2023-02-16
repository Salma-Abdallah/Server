package gov.iti.jets.network.controllers;


import gov.iti.jets.dto.requests.ChangeUserStatusRequest;
import gov.iti.jets.dto.responses.ChangeUserStatusResponse;
import gov.iti.jets.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OnlineStatusController extends Remote {

    void connect(User user, CallbackController callbackController) throws RemoteException;

    void disconnect(String phoneNumber) throws RemoteException;

    void ping() throws RemoteException;

    ChangeUserStatusResponse changeStatus(ChangeUserStatusRequest request) throws RemoteException;
}
