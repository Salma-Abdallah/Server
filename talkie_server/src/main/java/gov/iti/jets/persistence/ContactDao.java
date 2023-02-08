package gov.iti.jets.persistence;

import gov.iti.jets.persistence.connection.DataSourceSingleton;
import gov.iti.jets.entities.ContactEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    public int save(Integer userId, Integer contactId){
        insertRecord(userId, contactId);
        return insertRecord(contactId, userId);
    }

    public List<ContactEntity> getContactsByUserID(int userId) {
        String query = """
                            SELECT c.id c_id, c.category, u.*
                            FROM contacts c
                            INNER JOIN users u
                            ON u.id = c.contact_id
                            WHERE c.user_id = ?;
                       """;
        List<ContactEntity> contacts = new ArrayList<>();
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("c_id");
                String category = result.getString("category");
                contacts.add(new ContactEntity(id, userId, UserDao.resultSetToUserEntity(result), category));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public int delete(Integer userId, Integer contactId){
        String query = """
                            DELETE FROM contacts 
                            where (user_id = ? AND contact_id = ?) OR (user_id = ? AND contact_id = ?)
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, userId);
                statement.setInt(2, contactId);
                statement.setInt(3, contactId);
                statement.setInt(4, userId);
                return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int insertRecord(Integer userId, Integer contactId){
        String query = """
                            INSERT INTO contacts (user_id, contact_id, category)
                            VALUES (?, ?, ?);
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,userId);
            statement.setInt(2,contactId);
            statement.setString(3, null);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
