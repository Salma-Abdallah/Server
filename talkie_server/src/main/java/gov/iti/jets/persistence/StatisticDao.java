package gov.iti.jets.persistence;

import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.persistence.connection.DataSourceSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticDao {
    public int getNumberOfAllUsers() {
        int count = 0;
        String query = """
                SELECT COUNT(*) count
                FROM users;
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int getNumberOfAllMsg() {
        int count = 0;
        String query = """
                SELECT COUNT(*) count
                FROM messages;
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int getNumberOfOfflineUsers() {
        int count = 0;
        String query = """
                SELECT COUNT(*) count
                FROM users
                GROUP BY online_status
                HAVING online_status="offline";
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int getNumberOfMaleUsers() {
        int count = 0;
        String query = """
                SELECT gender,COUNT(*) count
                FROM users
                GROUP BY gender
                HAVING gender='M';
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public List<StatisticEntity> getDataByCountry() {
        List<StatisticEntity> statisticEntities = new ArrayList<>();
        String query = """
                SELECT country,COUNT(*) count
                FROM users
                GROUP BY country;
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String data = result.getString("country");
                int count = result.getInt("count");
                statisticEntities.add(new StatisticEntity(data, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statisticEntities;
    }
}
