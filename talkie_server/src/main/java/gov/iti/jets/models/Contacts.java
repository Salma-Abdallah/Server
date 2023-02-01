package gov.iti.jets.models;

public class Contacts {

    private User user;
    private User contact;


    private String category;
    private int id;


    public Contacts(String category, int id){
        this.category = category;
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

    public void setContact(User contact) {
        this.contact = contact;
    }

    public User getContact() {
        return contact;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}