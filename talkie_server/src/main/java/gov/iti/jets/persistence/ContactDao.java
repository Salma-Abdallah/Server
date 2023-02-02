package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.Contacts;
import gov.iti.jets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private static ContactDao contactDao = new ContactDao();
    private Connection connection;


    ContactDao() {
        try {
            connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ContactDao getContactDao(){
        return contactDao;
    }

//    public static int addContact(Contacts contact){
//        int result;
//        String query = """
//                INSERT INTO contacts (user_id, contact_id, category)
//                VALUES (?, ?, ?)""";
//        try(PreparedStatement statement = connection.prepareStatement(query)){
//            statement.setInt(1,0);
//            statement.setInt(2,contact.getContact().getId());
//            statement.setString(3, contact.getCategory());
//            result = statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }

    public List<User> getContactByUserID(int userId) {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT u.* FROM users u
                  INNER JOIN contact c
                  ON c.contact_id = u.id and c.user_id = """+userId+";";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String userName = result.getString("username");
                String password = result.getString("password");
                String phoneNumber = result.getString("phone_number");
                String email = result.getString("email");
                String gender = result.getString("gender");
                String country = result.getString("country");
                Date birthDate = result.getDate("birth_date");
                String onlineStatus = result.getString("online_status");
                String bio = result.getString("bio");
                byte[] img = result.getBytes("picture");

                users.add(new User(id, userName, password, phoneNumber, email, gender, country, birthDate, onlineStatus, bio, img));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
