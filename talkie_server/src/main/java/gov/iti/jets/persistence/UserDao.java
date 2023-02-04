package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.User;

import javax.swing.text.html.Option;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    public UserEntity save(UserEntity userEntity) {
        String query = """
                            INSERT INTO users (username,  phone_number, email, password, gender, country, birth_date,
                                                online_status, bio, picture, created_at, salt)
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEntity.getUserName());
            statement.setString(2, userEntity.getPassword());
            statement.setString(3, userEntity.getPhoneNumber());
            statement.setString(4, userEntity.getEmail());
            statement.setString(5, userEntity.getGender());
            statement.setString(6, userEntity.getCountry());
            statement.setString(7, userEntity.getBirthDate().toString());
            statement.setString(8, userEntity.getOnlineStatus());
            statement.setString(9, userEntity.getBio());
            statement.setString(10, userEntity.getPictureUrl());
            statement.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(12,userEntity.getSalt());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntity;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> userEntities = new ArrayList<>();
        String query = """
                            SELECT id, username, phone_number, email, picture, password, gender,
                            country, birth_date, created_at, online_status, bio, salt FROM users
                        """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userEntities.add(resultSetToUserEntity(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntities;
    }

    public Optional<UserEntity> findUserById(Integer userId) {
        String query = """
                        SELECT * FROM users where id = ?
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return Optional.of(resultSetToUserEntity(result));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(int id, UserEntity userEntity) {
        int result;
        String query = """
                        UPDATE users SET username = ?, email = ?, created_at = ?,
                        phone_number = ?, password = ?, gender = ?, birth_date = ?,
                        country = ?, online_status = ?, bio = ?, picture = ? WHERE ID = ?
                       """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEntity.getUserName());
            statement.setString(2, userEntity.getEmail());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(4, userEntity.getPhoneNumber());
            statement.setString(5, userEntity.getPassword());
            statement.setString(6, userEntity.getGender());
            statement.setString(7, userEntity.getBirthDate().toString());
            statement.setString(8, userEntity.getCountry());
            statement.setString(9, userEntity.getOnlineStatus());
            statement.setString(10, userEntity.getBio());
            statement.setString(11, userEntity.getPictureUrl());
            statement.setInt(12, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static UserEntity resultSetToUserEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        String country = resultSet.getString("country");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        String onlineStatus = resultSet.getString("online_status");
        String bio = resultSet.getString("bio");
        String salt = resultSet.getString("salt");
        String imageUrl = resultSet.getString("picture");
        return new UserEntity(id, userName, phoneNumber, email, password, gender, country, birthDate, onlineStatus, bio, imageUrl, createdAt, salt);
    }
}
