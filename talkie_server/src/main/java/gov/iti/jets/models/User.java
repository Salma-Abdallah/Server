package gov.iti.jets.models;
import java.sql.Date;
import java.sql.Timestamp;


public class User {
    private String userName;
    private int id;
    private String email;
    private String phoneNumber;
    private String passWord;
    private String gender;
    private String country;
    private Date birthDate;
    private String onlineStatus;
    private String bio;
    private byte picture[];
    private Timestamp creationTime;

    /*------------------------------------------------------------------------------------------------ */
    public User(int id, String userName, Timestamp creationTime,String passWord, String phoneNumber, String email, String gender, String country,
    Date birthDate, String onlineStatus, String bio, byte[] picture) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.onlineStatus = onlineStatus;
        this.bio = bio;
        this.picture=picture;
        this.creationTime = creationTime;

    }



    /*------------------------------------------------------------------------------------------------ */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /*------------------------------------------------------------------------------------------------ */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setBirthDate (Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setPicture(byte picture[]) {
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;

    }
    /*------------------------------------------------------------------------------------------------ */

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}