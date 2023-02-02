package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {

    public List<User> getUsersByGroupChatId(int groupId) {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT u.* FROM users u
                  INNER JOIN user_group ug
                  ON u.id = ug.user_id and ug.group_chat_id =?
                  """;

        try(Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,groupId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
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

                users.add(new User(id, userName,time ,password, phoneNumber, email, gender, country, birthDate, onlineStatus, bio, img));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    //TODO: Edit Code depending on Models
    public List<GroupChat> getGroupsChatByUserID(int userId) {
        List<GroupChat> groupChat = new ArrayList<>();
        String query = """
                SELECT gc.* FROM group_chat gc
                INNER JOIN user_group ug
                ON gc.id = ug.group_chat_id and ug.user_id =?""";

        try (Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                //groupChat.add(new GroupChat(result.getInt("id"), result.getString("name")));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupChat;
    }

}

