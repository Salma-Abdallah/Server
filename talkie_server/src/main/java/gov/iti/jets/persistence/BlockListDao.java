package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlockListDao {

    //TODO: Make a Connection per Query and close it right after using try with resources.
        private static BlockListDao blockListDao = new BlockListDao();
        private Connection connection;

        BlockListDao() {
            try {
                connection = DataSourceSingleton.INSTANCE.getDataSource().getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static BlockListDao getBlockListDao(){
            return blockListDao;
        }

        //TODO: use prepared statement.
        public List<User> getBlockListByUserID(int userId) {
            List<User> users = new ArrayList<>();
            //we need to check with real data
            String query = """
                SELECT u.* FROM users u
                  INNER JOIN block_list bl
                  ON bl.blocked_user_id = u.id and bl.user_id = """+userId+";";

            try(PreparedStatement statement = connection.prepareStatement(query)){
                ResultSet result = statement.executeQuery();
                while (result.next()){
                    int id = result.getInt("id");
                    String userName = result.getString("username");
                    String password = result.getString("password");
                    String phoneNumber = result.getString("phone_number");
                    String email = result.getString("email");
                    String gender = result.getString("gender");
                    String country = result.getString("country");
                    Date birthDate = result.getDate("birth_date");
                    String onlineStatus = result.getString("online_status");
                    String bio = result.getString("bio");
                    byte[] img = result.getBytes("picture");

                    users.add(new User(id, userName, password, phoneNumber, email, gender, country, birthDate, onlineStatus, bio, img));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return users;
        }

    }

