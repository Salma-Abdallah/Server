package gov.iti.jets.services;

import gov.iti.jets.entities.ContactEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.ContactMapper;
import gov.iti.jets.models.Contact;
import gov.iti.jets.persistence.ContactDao;

import java.util.ArrayList;
import java.util.List;

public class ContactServices {
    private UserEntity userEntity;
    private ContactEntity contactEntity;
    private ContactDao contactDao = new ContactDao();
    ContactMapper contactMapper = new ContactMapper();

    public int addNewContact(String userPhoneNumber, String contactPhoneNumber) {
        return contactMapper.save(userPhoneNumber,contactPhoneNumber);
    }
    public List<Contact> getContactsByUserID(String userPhoneNumber) {
        return contactMapper.getContactsByUserID(userPhoneNumber);
    }
    public int deleteContact(String userPhoneNumber, String contactPhoneNumber) {
        return contactMapper.delete(userPhoneNumber, contactPhoneNumber);
    }

}
