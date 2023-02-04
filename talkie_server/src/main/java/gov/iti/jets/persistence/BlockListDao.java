package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.entities.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlockListDao {

    public BlockListEntity save(BlockListEntity blockListEntity){
        String query = """
                            INSERT INTO block_list(user_id, blocked_user_id) VALUES(?, ?)
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, blockListEntity.getUserId());
                statement.setInt(2, blockListEntity.getBlockedUserId());
                statement.executeUpdate();
                return blockListEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<UserEntity> findAllBlockedUsersByUserId(Integer userId) {
        String query = """
                            SELECT u.* FROM block_list bl
                            INNER JOIN users u
                            ON bl.blocked_user_id = u.id
                            WHERE bl.user_id = ?
                        """;
        return getUserEntities(userId, query);
    }

    public List<UserEntity> findAllBlockersByBlockedUserId(Integer blockedUserId){
        String query = """
                            SELECT u.* 
                            FROM block_list bl
                            INNER JOIN users u
                            ON u.id = bl.user_id 
                            WHERE bl.blocked_user_id = ?
                       """;
        return getUserEntities(blockedUserId, query);
    }

    private List<UserEntity> getUserEntities(Integer blockedUserId, String query) {
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            List<UserEntity> userEntities = new ArrayList<>();
            statement.setInt(1, blockedUserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userEntities.add(UserDao.resultSetToUserEntity(resultSet));
            }
            return userEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

