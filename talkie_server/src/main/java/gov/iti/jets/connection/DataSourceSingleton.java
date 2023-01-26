package gov.iti.jets.connection;

import com.mysql.cj.jdbc.MysqlDataSource;
import gov.iti.jets.TalkieApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum DataSourceSingleton {
    INSTANCE;
    MysqlDataSource dataSource;

    public MysqlDataSource getDataSource() {
        return dataSource;
    }
    DataSourceSingleton(){
        try (InputStream inputStream = TalkieApplication.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
