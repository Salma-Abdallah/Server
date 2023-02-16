package gov.iti.jets.models;

public class CurrentAdmin {
    private static CurrentAdmin instance = new CurrentAdmin();
    private Admin admin = new Admin();

    public static CurrentAdmin getInstance() {
        return instance;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
