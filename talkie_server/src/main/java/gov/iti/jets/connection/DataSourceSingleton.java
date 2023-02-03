package gov.iti.jets.connection;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import gov.iti.jets.TalkieApplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public enum DataSourceSingleton {
    INSTANCE;
    MysqlConnectionPoolDataSource dataSource;
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    DataSourceSingleton(){
        try (InputStream inputStream = TalkieApplication.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setURL(props.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}