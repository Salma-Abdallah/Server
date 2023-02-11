package gov.iti.jets.persistence;

import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.connection.DataSourceSingleton;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatisticDao {
    public List<StatisticEntity> getOnlineOfflineUsers() {
        List<StatisticEntity> statisticEntities = new ArrayList<>();
        String query = """
                SELECT status,COUNT(*) count
                FROM users
                GROUP BY gender
                HAVING online_status="online" OR online_status="offline";
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String data = result.getString("status");
                int count = result.getInt("count");
                statisticEntities.add(new StatisticEntity(data, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statisticEntities;
    }
    public List<StatisticEntity> getDataByGender() {
        List<StatisticEntity> statisticEntities = new ArrayList<>();
        String query = """
                SELECT gender,COUNT(*) count
                FROM users
                GROUP BY gender;
                """;
        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String data = result.getString("gender");
                int count = result.getInt("count");
                statisticEntities.add(new StatisticEntity(data, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statisticEntities;
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
