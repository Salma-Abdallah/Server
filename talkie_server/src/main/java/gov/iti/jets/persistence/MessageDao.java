package gov.iti.jets.persistence;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.MessageEntity;
import gov.iti.jets.entities.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    UserDao userDao = new UserDao();
    public int saveMessage(MessageEntity messageEntity){
        String query = """
                            INSERT INTO messages(chat_id, author_id, font_style, font_color,
                             font_size, bold, italic, text_background, underlined, sent_at, content, file_url)
                            values(?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (Connection connection = DataSourceSingleton.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, messageEntity.getChatId());
            statement.setInt(2, messageEntity.getAuthor().getId());
            statement.setString(3, messageEntity.getFontStyle());
            statement.setString(4, messageEntity.getFontColor());
            statement.setDouble(5, messageEntity.getFontSize());
            statement.setBoolean(6, messageEntity.isBold());
            statement.setBoolean(7, messageEntity.isItalic());
            statement.setString(8, messageEntity.getTextBackground());
            statement.setBoolean(9, messageEntity.isUnderlined());
            statement.setTimestamp(10, messageEntity.getSentAt());
            statement.setString(11, messageEntity.getContent());
            statement.setString(12, messageEntity.getFileUrl());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<MessageEntity> findMessagesByChatID(String chatId){
        String query = """
                            SELECT *
                            FROM messages
                            WHERE chat_id = ?;
                       """;
        List<MessageEntity> messageEntities = new ArrayList<>();
        try(Connection connection = DataSourceSingleton.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, chatId);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                int id = result.getInt("id");
                UserEntity author = userDao.findUserById(result.getInt("author_id")).get();
                String fontStyle = result.getString("font_style");
                String fontColor = result.getString("font_color");
                double fontSize = result.getDouble("font_size");
                boolean isBold = result.getBoolean("bold");
                boolean isItalic = result.getBoolean("italic");
                String textBackground = result.getString("text_background");
                boolean isUnderlined = result.getBoolean("underlined");
                Timestamp sentAt = result.getTimestamp("sent_at");
                String content = result.getString("content");
                String fileUrl = result.getString("file_url");

                messageEntities.add(new MessageEntity(id, author, chatId, fontStyle, fontColor, fontSize, isBold, isItalic, isUnderlined, textBackground, sentAt , content, fileUrl));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messageEntities;
    }
}
