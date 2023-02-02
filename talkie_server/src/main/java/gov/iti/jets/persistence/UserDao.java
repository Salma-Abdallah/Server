package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao {

    public int insert(User user) {
        int result;
        String query = """
                INSERT INTO USERS (username, password, phone_number, email, gender, country, birth_date, online_status, bio, picture)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassWord());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getCountry());
            statement.setString(7, user.getBirthDate().toString());
            statement.setString(8, user.getOnlineStatus());
            statement.setString(9, user.getBio());
            FileInputStream img = new FileInputStream(Arrays.toString(user.getPicture()));
            statement.setBlob(10,img);

            result = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM USERS";
        try (Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String userName = result.getString("username");
                Timestamp time = result.getTimestamp("created_at");
                String password = result.getString("password");
                String phoneNumber = result.getString("phone_number");
                String email = result.getString("email");
                String gender = result.getString("gender");
                String country = result.getString("country");
                Date birthDate = result.getDate("birth_date");
                String onlineStatus = result.getString("online_status");
                String bio = result.getString("bio");
                Blob bloImg = result.getBlob("picture");
                byte[] img = bloImg.getBytes(1,(int)bloImg.length());

                users.add(new User(id, userName, time, password, phoneNumber, email, gender, country, birthDate, onlineStatus, bio, img));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserById(int userId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM USERS where id = ?";
        try (Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            int id = result.getInt("id");
            String userName = result.getString("username");
            Timestamp time = result.getTimestamp("created_at");
            String password = result.getString("password");
            String phoneNumber = result.getString("phone_number");
            String email = result.getString("email");
            String gender = result.getString("gender");
            String country = result.getString("country");
            Date birthDate = result.getDate("birth_date");
            String onlineStatus = result.getString("online_status");
            String bio = result.getString("bio");
            Blob bloImg = result.getBlob("picture");
            byte[] img = bloImg.getBytes(1,(int)bloImg.length());

            connection.close();
            return new User(id, userName, time, password, phoneNumber, email, gender, country, birthDate, onlineStatus, bio, img);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(int id, User newUser) {
        int result;
        String query = """
                UPDATE USERS SET username = ?, email = ?, created_at = ?
                phone_number = ?, password = ?, gender = ?, birth_date = ?,
                country = ?, online_status = ?, bio = ?, picture = ? WHERE ID = ?""";
        try (Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newUser.getUserName());
            statement.setString(2, newUser.getEmail());
            statement.setTimestamp(3, newUser.getCreationTime());
            statement.setString(4, newUser.getPhoneNumber());
            statement.setString(5, newUser.getPassWord());
            statement.setString(6, newUser.getGender());
            statement.setString(7, newUser.getBirthDate().toString());
            statement.setString(8, newUser.getCountry());
            statement.setString(9, newUser.getOnlineStatus());
            statement.setString(10, newUser.getBio());
            FileInputStream img = new FileInputStream(Arrays.toString(newUser.getPicture()));
            statement.setBlob(11,img);
            statement.setInt(12, id);

            result = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
