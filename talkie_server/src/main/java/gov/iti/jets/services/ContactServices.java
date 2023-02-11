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

    public int addNewContact(Integer userId, Integer contactId) {
        return contactDao.save(userId, contactId);
    }
    public List<Contact> getContactsByUserID(int userId) {
        List <Contact> contactList = new ArrayList<>();
        List <ContactEntity> contactEntities = contactDao.getContactsByUserID(userId);
        ContactMapper contactMapper = new ContactMapper();

        for (ContactEntity contactEntity : contactEntities ){
            contactList.add(contactMapper.entityToModel(contactEntity));
        }
        return contactList;
    }
    public int delectContact(Integer userId, Integer contactId) {
        return contactDao.delete(userId, contactId);
    }

}
