package gov.iti.jets.persistence;

import gov.iti.jets.models.Chat;
import gov.iti.jets.persistence.connection.DataSourceSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatDao {

    public String save(String uuid){
        String query = """
                             INSERT INTO chat(id) VALUES (?)
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, uuid);
            statement.executeUpdate();
            return uuid;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Chat> findChatById(String uuid){
        String query = """
                            SELECT * FROM chat WHERE id = ?
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Chat(resultSet.getString("id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String id){
        String query = """
                        DELETE FROM chat WHERE id = ?
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                return statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }
}
