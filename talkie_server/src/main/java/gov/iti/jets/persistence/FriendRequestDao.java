package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDao {
    private static FriendRequestDao friendRequestDao = new FriendRequestDao();
    private Connection connection;

    FriendRequestDao() {
        try {
            connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static FriendRequestDao getFriendRequestDao(){
        return friendRequestDao;
    }

    public List<User> getFriendRequestByUserID(int userId) {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT u.* FROM users u
                  INNER JOIN friend_request f
                  ON f.receiver_id = u.id and f.sender_id = """+userId+";";

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
