package gov.iti.jets.persistence;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.persistence.connection.DataSourceSingleton;

import java.sql.*;
import java.util.Optional;

public class AdminDao {

//    public AdminEntity save(AdminEntity adminEntity) {
//        String query = """
//                    INSERT INTO admins (admin_name, phone_number, password, salt)
//                    VALUES (?, ?, ?, ?)
//                """;
//        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, adminEntity.getAdminName());
//            statement.setString(2, adminEntity.getPhoneNumber());
//            statement.setString(4, adminEntity.getPassword());
//            statement.setString(12, adminEntity.getSalt());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return adminEntity;
//    }

    public Optional<AdminEntity> findAdminByPhoneNumber(String phone) {
        String query = """
                 SELECT * FROM admins where phone_number = ?
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phone);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String adminName = result.getString("admin_name");
                String phoneNumber = result.getString("phone_number");
                String password = result.getString("password");
                String salt = result.getString("salt");
                return Optional.of(new AdminEntity(id, adminName, phoneNumber, password, salt));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}