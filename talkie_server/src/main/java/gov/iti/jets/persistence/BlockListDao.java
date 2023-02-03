package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.BlockListEntity;
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
    public List<BlockListEntity> findAllBlockedUsersByUserId(Integer userId) {
        String query = """
                            SELECT * FROM block_list WHERE user_id =  ?
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            List<BlockListEntity> blockListEntities = new ArrayList<>();
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int blockedUserId = resultSet.getInt("blocked_user_id");
                blockListEntities.add(new BlockListEntity(id ,userId, blockedUserId));
            }
            return blockListEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BlockListEntity> findAllBlockersByBlockedUserId(Integer blockedUserId){
        String query = """
                            SELECT * FROM block_list WHERE blocked_user_id =  ?
                       """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            List<BlockListEntity> blockListEntities = new ArrayList<>();
            statement.setInt(1, blockedUserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                blockListEntities.add(new BlockListEntity(id ,userId, blockedUserId));
            }
            return blockListEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

