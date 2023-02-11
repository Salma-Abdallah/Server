package gov.iti.jets.network.controllers;

import gov.iti.jets.models.Contact;

import java.rmi.Remote;
import java.util.List;

public interface ContactController extends Remote {

    int addNewContact(Integer userId, Integer contactId);
    List<Contact> getContactsByUserID(int userId);
    int delectContact(Integer userId, Integer contactId);

}
