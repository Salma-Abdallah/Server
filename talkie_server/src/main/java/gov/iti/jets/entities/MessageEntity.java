package gov.iti.jets.entities;


import java.sql.Timestamp;

public class MessageEntity {

    private Integer id;

    private UserEntity author;

    private String chatId;
    private String fontStyle;
    private String fontColor;
    private double fontSize;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private String textBackground;
    private Timestamp sentAt;
    private String content;
    private String fileUrl;

    public MessageEntity(Integer id, UserEntity author, String chatId, String fontStyle, String fontColor,
                         double fontSize, boolean bold, boolean italic, boolean underlined, String textBackground,
                         Timestamp sentAt, String content, String fileUrl) {
        this.id = id;
        this.author = author;
        this.chatId = chatId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.textBackground = textBackground;
        this.sentAt = sentAt;
        this.content = content;
        this.fileUrl = fileUrl;
    }

    public MessageEntity(UserEntity author, String chatId, String fontStyle, String fontColor, double fontSize,
                         boolean bold, boolean italic, boolean underlined, String textBackground,
                         Timestamp sentAt, String content, String fileUrl) {
        this.author = author;
        this.chatId = chatId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.textBackground = textBackground;
        this.sentAt = sentAt;
        this.content = content;
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public String getTextBackground() {
        return textBackground;
    }

    public void setTextBackground(String textBackground) {
        this.textBackground = textBackground;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", author=" + author +
                ", chatId='" + chatId + '\'' +
                ", fontStyle='" + fontStyle + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", fontSize=" + fontSize +
                ", bold=" + bold +
                ", italic=" + italic +
                ", underlined=" + underlined +
                ", textBackground='" + textBackground + '\'' +
                ", sentAt=" + sentAt +
                ", content='" + content + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}