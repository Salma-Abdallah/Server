package gov.iti.jets.models;
import java.sql.Date;



public class User {
    private String username;
    private int id;
    private String email;
    private String phone_number;
    private String password;
    private String gender;
    private String country;
    private Date birth_date;
    private String online_status;
    private String bio;
    private byte picture[];

    /*------------------------------------------------------------------------------------------------ */
    public Client(int id,String username, String password, String phone_number, String email, String gender, String country,
    Date birth_date, String online_status, String bio, byte[] picture) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.gender = gender;
        this.birth_date = birth_date;
        this.country = country;
        this.online_status = online_status;
        this.bio = bio;
        this.picture=picture;
    }



    /*------------------------------------------------------------------------------------------------ */

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /*------------------------------------------------------------------------------------------------ */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public void setBirth_date (Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getBirth_date() {
        return birth_date;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getOnline_status() {
        return online_status;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setImage(byte picture[]) {
        this.picture = picture;
    }

    public byte[] getImage() {
        return picture;

    }
    /*------------------------------------------------------------------------------------------------ */

    public String toString() {
        return "User [id=" + id + ", User Name=" + username + ", phone number=" + phone_number + ",email= " + email
                + ",passWord= " + password + ",gender= " + gender + ",country=" + country + ",birth date= " + birth_date
                + ",online Status=" + online_status + ",bio=" + bio + ",picture=" + picture + "]";
    }

}