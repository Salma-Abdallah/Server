package gov.iti.jets.models;

public class Contacts {




    private String category;
    private int id;
    private int userID;
    private int contactID;


    public Contacts(String category, int id, int userID, int contactID){
        this.category = category;
        this.contactID = contactID;
        this.userID = userID;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }


    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}