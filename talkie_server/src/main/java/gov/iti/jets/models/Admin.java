package gov.iti.jets.models;

public class Admin {
    private String adminName;
    private String phoneNumber;
    private String password;
    public Admin() {}

    public Admin(String adminName, String phoneNumber, String password) {
        this.adminName = adminName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
