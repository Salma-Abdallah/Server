package gov.iti.jets.dto.responses;

import gov.iti.jets.models.Contact;

import java.io.Serializable;
import java.util.List;

public class ContactResponse implements Serializable {

    private static final long serialVersionUID= 546321469785312L;
    private String category;
    private String phoneNumber;
    private int result;
    private List<Contact> contactList;

    public ContactResponse(int result){
        this.result= result;
    }
    public ContactResponse(List<Contact>contactList){
        this.contactList= contactList;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    public String toString() {
        return "FriendReqRequest" +
                "phoneNumber='" + phoneNumber + '\'' +
                "category='" + category + '\'' +
                '}';
    }
}
