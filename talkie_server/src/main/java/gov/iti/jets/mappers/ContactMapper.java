package gov.iti.jets.mappers;

import gov.iti.jets.entities.ContactEntity;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.models.Contact;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.persistence.ContactDao;
import gov.iti.jets.persistence.FriendRequestDao;

import java.util.List;

public class ContactMapper {

    private ContactDao contactDao;
    public ContactMapper() {
        contactDao = new ContactDao();
    }
    public int save(Integer userId, Integer contactId) {

    return contactDao.save(userId, contactId);
    }

    public List<ContactEntity> getContactsByUserID(int userId) {
        return contactDao.getContactsByUserID(userId);
    }

    public int delete(Integer userId, Integer contactId) {
        return contactDao.delete(userId, contactId);
    }

    public Contact entityToModel(ContactEntity contactEntity) {
        Contact contact = new Contact();
        contact.setCategory(contactEntity.getCategory());
        return contact;
    }
    public ContactEntity modelToEntity (Contact contact) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setCategory(contact.getCategory());
        return contactEntity;
    }




}
