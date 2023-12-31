package gov.iti.jets.persistence;

import gov.iti.jets.persistence.connection.DataSourceSingleton;
import gov.iti.jets.entities.RegularChatEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RegularChatDao {

    public RegularChatEntity save(RegularChatEntity regularChatEntity){
        String query = """
                            INSERT INTO regular_chat(id, first_participant_id, second_participant_id)
                            values(?, ?, ?)
                       """;
        String uuid = UUID.randomUUID().toString();
        ChatDao chatDao = new ChatDao();
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, chatDao.save(uuid));
            statement.setInt(2, regularChatEntity.getFirstParticipantId());
            statement.setInt(3, regularChatEntity.getSecondParticipantId());
            statement.executeUpdate();
            regularChatEntity.setId(uuid);
            return regularChatEntity;
        } catch (SQLException e) {
            chatDao.delete(uuid);
            throw new RuntimeException(e);
        }
    }
    public List<RegularChatEntity> findAllRegChatsByUserID(int userId) {
        List<RegularChatEntity> regularChatEntities = new ArrayList<>();
        String query = """
                            SELECT * FROM regular_chat where first_participant_id = ? or second_participant_id = ?
                       """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,userId);
            statement.setInt(2,userId);

            ResultSet result = statement.executeQuery();
            while (result.next()){
                String chat_id = result.getString("id");
                int firstParticipantId = result.getInt("first_participant_id");
                int secondParticipantId = result.getInt("second_participant_id");
                regularChatEntities.add(new RegularChatEntity(chat_id,firstParticipantId,secondParticipantId));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return regularChatEntities;
    }

    public int delete(String id){
        String query = """
                            DELETE FROM regular_chat WHERE id = ?
                        """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, id);
            statement.executeUpdate();
            return new ChatDao().delete(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<RegularChatEntity> findRegularChatByParticipantsIds(Integer firstParticipantId, Integer secondParticipantId ){
        String query = """
                            SELECT * FROM regular_chat rc
                            WHERE (rc.first_participant_id = ? AND rc.second_participant_id = ?)
                                    OR (rc.first_participant_id = ? AND rc.second_participant_id = ?)
                        """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, firstParticipantId);
            statement.setInt(2, secondParticipantId);
            statement.setInt(3, secondParticipantId);
            statement.setInt(4, firstParticipantId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new RegularChatEntity(resultSet.getString("id"),
                                                         resultSet.getInt("first_participant_id"),
                                                         resultSet.getInt("second_participant_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
