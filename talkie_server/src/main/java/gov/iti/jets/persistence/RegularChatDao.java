package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.Chat;
import gov.iti.jets.models.Regular_Chat;
import gov.iti.jets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegularChatDao {

    //TODO: Khaled: Check if you have to insert two rows per 2 users so that the user can see chats on both directions.


    public List<User> getAllRegChatsByUserID(int userId) {
        //TODO: Comment for handling bidirectional data.
        List<User> users = new ArrayList<>();
        String query = """
                SELECT * FROM regular_chat
                where first_participant_id = ?""";
        try(Connection connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int sParticipant_id = result.getInt("second_participant_id");

                //TODO: change this to a userdao instance
                // users.add(UserDao.getUserDao().getUserById(sParticipant_id));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


}
