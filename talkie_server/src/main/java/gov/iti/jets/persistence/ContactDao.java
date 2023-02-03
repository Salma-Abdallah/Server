package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.ContactEntity;
import gov.iti.jets.models.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    public int save(ContactEntity contactEntity){
        insertRecord(contactEntity.getUserId(), contactEntity.getContactId(), contactEntity.getCategory());
        return insertRecord(contactEntity.getContactId(), contactEntity.getUserId(), contactEntity.getCategory());
    }

    public List<Contact> getContactsByUserID(int userId) {
        String query = """
                            SELECT * FROM contacts
                            WHERE user_id = ?
                       """;
        List<Contact> contacts = new ArrayList<>();
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                int contactId = result.getInt("contact_id");
                String category = result.getString("category");
                contacts.add(new Contact(id, userId, contactId, category));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public int delete(ContactEntity contactEntity){
        String query = """
                            DELETE FROM contacts 
                            where (user_id = ? AND contact_id = ?) OR (user_id = ? AND contact_id = ?)
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, contactEntity.getUserId());
                statement.setInt(2, contactEntity.getContactId());
                statement.setInt(3, contactEntity.getContactId());
                statement.setInt(4, contactEntity.getUserId());
                return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int insertRecord(Integer userId, Integer contactId, String category){
        String query = """
                            INSERT INTO contacts (user_id, contact_id, category)
                            VALUES (?, ?, ?);
                        """;
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,userId);
            statement.setInt(2,contactId);
            statement.setString(3, category);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
