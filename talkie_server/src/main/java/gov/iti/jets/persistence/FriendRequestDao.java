package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDao {


    public FriendRequestEntity save(FriendRequestEntity friendRequestEntity){
        String query = """
                            INSERT INTO friend_request(sender_id, receiver_id, status, sent_at) VALUES (?, ?, ?, ?)
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, friendRequestEntity.getSenderId());
                statement.setInt(2, friendRequestEntity.getReceiverId());
                statement.setBoolean(3, friendRequestEntity.getStatus());
                statement.setTimestamp(4, friendRequestEntity.getSentAt());
                statement.executeUpdate();
                return friendRequestEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FriendRequestEntity> findSentFriendRequestByUserID(Integer userId) {
        String query = """
                            SELECT id, receiver_id, status, sent_at
                            FROM friend_request WHERE sender_id = ?
                       """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            List<FriendRequestEntity> friendRequests = new ArrayList<>();
            while (result.next()){
                int id = result.getInt("id");
                int receiverId = result.getInt("receiver_id");
                boolean requestStatus = result.getBoolean("status");
                Timestamp sentAt = result.getTimestamp("sent_at");
                friendRequests.add(new FriendRequestEntity(id, userId, receiverId, requestStatus, sentAt));
            }
            return friendRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FriendRequestEntity> findReceivedFriendRequestByUserID(Integer userId) {
        String query = """
                           SELECT id, sender_id, status, sent_at
                           FROM friend_request WHERE receiver_id = ?
                       """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            List<FriendRequestEntity> friendRequests = new ArrayList<>();
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                int senderId = result.getInt("sender_id");
                boolean requestStatus = result.getBoolean("status");
                Timestamp sentAt = result.getTimestamp("sent_at");
                friendRequests.add(new FriendRequestEntity(id, senderId, userId, requestStatus, sentAt));
            }
            return friendRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int delete(FriendRequestEntity friendRequestEntity){
        String query = """
                            DELETE FROM friend_request WHERE sender_id = ? AND receiver_id = ?;
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, friendRequestEntity.getSenderId());
            statement.setInt(2, friendRequestEntity.getReceiverId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
