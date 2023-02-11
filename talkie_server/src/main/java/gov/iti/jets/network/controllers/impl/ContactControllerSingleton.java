package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.models.Contact;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ContactServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ContactControllerSingleton extends UnicastRemoteObject implements ContactController {

    private static ContactControllerSingleton instance;
    private ContactServices contactServices;

    protected ContactControllerSingleton() throws RemoteException {
        super();
    }

    public static ContactControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new ContactControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("ContactController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public  int addNewContact(Integer userId, Integer contactId) throws RemoteException{


        return contactServices.addNewContact(userId, contactId);
    }

    public  List<Contact> getContactsByUserID(int userId) throws RemoteException{
        return contactServices.getContactsByUserID(userId);


    }
    public int delectContact(Integer userId, Integer contactId) throws RemoteException{
        return  contactServices.delectContact(userId,contactId);

    }




}
