package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.ContactRequest;
import gov.iti.jets.dto.responses.ContactResponse;
import gov.iti.jets.models.Contact;

import gov.iti.jets.network.controllers.ContactController;
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



    @Override
    public ContactResponse addNewContact(ContactRequest contactRequest) {
       return new ContactResponse(new ContactServices().addNewContact(contactRequest.getUserPhoneNumber(),contactRequest.getContactPhoneNumber()));
    }


    @Override
    public ContactResponse getContactsByUserID(ContactRequest contactRequest) {
        return  new ContactResponse(new ContactServices().getContactsByUserID(contactRequest.getUserPhoneNumber()));
    }

    @Override
    public ContactResponse deleteContact(ContactRequest contactRequest) {
        return new ContactResponse(new ContactServices().deleteContact(contactRequest.getUserPhoneNumber(),contactRequest.getContactPhoneNumber()));

    }

    public static ContactControllerSingleton getInstance() {
        try {
            if (instance == null) {
                instance = new ContactControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("ContactController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

}

