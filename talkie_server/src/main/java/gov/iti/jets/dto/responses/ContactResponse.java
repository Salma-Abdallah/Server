package gov.iti.jets.dto.responses;

import java.io.Serializable;

public class ContactResponse implements Serializable {

    private static final long serialVersionUID= 546321469785312L;
    private String category;
    private String phoneNumber;

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
