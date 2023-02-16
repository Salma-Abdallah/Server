package gov.iti.jets.network.controllers;

import gov.iti.jets.models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackController extends Remote {

    void respond() throws RemoteException;

    void receiveNewMessage(Message message) throws RemoteException;
    void friendOnlineStatus(String chatId, String status) throws RemoteException;

    void createNewFriendRequest(FriendRequest friendRequest) throws RemoteException;
    void createNewRegularChat(RegularChat chat) throws RemoteException;

    void deleteRecievedFriendRequest(String senderPhoneNumber) throws RemoteException;

    // When someone is added to a groupchat this is called so that the group chat appears on this person's panel.
    void addCurrentUserToGroupChat(GroupChat chat) throws RemoteException;

    // when a notification is sent on broadcast to all online users.
    void broadcastNotification(String message) throws RemoteException;
}