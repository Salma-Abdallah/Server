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
    private byte image[];
    /*------------------------------------------------------------------------------------------------ */

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(String username) {
        return username;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setId(int id) {
        this.id = id;
    }

    public int getid(int id) {
        return id;
    }

    /*------------------------------------------------------------------------------------------------ */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number(String phone_number) {
        return phone_number;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(String email) {
        return email;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(String password) {
        return password;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender(String gender) {
        return gender;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry(String country) {
        return country;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getBirth_date(Date birth_date) {
        return birth_date;
    }
    /*------------------------------------------------------------------------------------------------ */

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getOnline_status(String online_status) {
        return online_status;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio(String bio) {
        return bio;
    }

    /*------------------------------------------------------------------------------------------------ */

    public void setImage(byte image[]) {
        this.image = image;
    }

    public byte[] getImage(byte image[]) {
        return image;

    }
    /*------------------------------------------------------------------------------------------------ */

}


