package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.requests.ContactRequest;
import gov.iti.jets.dto.responses.ContactResponse;
import gov.iti.jets.models.Contact;

import java.rmi.Remote;
import java.util.List;

public interface ContactController extends Remote {

    ContactResponse addNewContact(ContactRequest contactRequest);
    ContactResponse getContactsByUserID(ContactRequest contactRequest);
    ContactResponse deleteContact(ContactRequest contactRequest);

}
