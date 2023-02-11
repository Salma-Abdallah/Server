package gov.iti.jets.mappers;

import gov.iti.jets.entities.ContactEntity;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.Contact;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.persistence.ContactDao;
import gov.iti.jets.persistence.FriendRequestDao;
import gov.iti.jets.persistence.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactMapper {
    private UserDao userDao;
    private ContactDao contactDao;
    public ContactMapper() {
        contactDao = new ContactDao();
        userDao = new UserDao();
    }
    public int save(String userPhoneNumber, String contactPhoneNumber) {
        Optional<UserEntity> contactEntityOptional = userDao.findUserByPhoneNumber(contactPhoneNumber);
        if(contactEntityOptional.isPresent()){
            UserEntity contactEntity = contactEntityOptional.get();
            Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
            if(userEntityOptional.isPresent()) {
                UserEntity userEntity = userEntityOptional.get();
                return contactDao.save(userEntity.getId(),contactEntity.getId());
            }
        }
        else {
            System.out.println("yarab");
        }
        return 0;
    }


    public int delete(String userPhoneNumber, String contactPhoneNumber) {

        Optional<UserEntity> contactEntityOptional = userDao.findUserByPhoneNumber(contactPhoneNumber);
        //to be asked
        if(contactEntityOptional.isPresent()){
            UserEntity contactEntity = contactEntityOptional.get();
            Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
            if(userEntityOptional.isPresent()) {
                UserEntity userEntity = userEntityOptional.get();
                return contactDao.delete(userEntity.getId(),contactEntity.getId());
            }
        }
        else {
            System.out.println("y4t8l");
        }
        return 0;
    }
    public List<Contact> getContactsByUserID(String userPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            List<ContactEntity> contactEntities = contactDao.getContactsByUserID(userEntity.getId());
            List<Contact> contactList = new ArrayList<>();

            for (ContactEntity contactEntity : contactEntities) {
                contactList.add(entityToModel(contactEntity));

            }
            return contactList;
        }
        return null;
    }

    public static Contact entityToModel(ContactEntity contactEntity) {
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
