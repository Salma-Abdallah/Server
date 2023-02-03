package gov.iti.jets.entities;

public class ContactEntity {
    private Integer id;
    private Integer userId;
    private Integer contactId;
    private String category;


    public ContactEntity(Integer id, Integer userId, Integer contactId, String category) {
        this.id = id;
        this.userId = userId;
        this.contactId = contactId;
        this.category = category;
    }

    public ContactEntity(Integer userId, Integer contactId) {
        this.userId = userId;
        this.contactId = contactId;
        this.category = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}