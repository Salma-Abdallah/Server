package gov.iti.jets.models;

import com.mysql.cj.protocol.x.XMessage;

import java.sql.Timestamp;

public class Messages {

    private Chat chat;
    private User user;



    private int id;
    private String content;
    private String fontStyle;
    private String fontColor;
    private String textBackground;
    private double fontSize;
    private String fontWeight;
    private byte underLined;


    private Timestamp messageTime;

    /*------------------------------------------------------------------------------------------*/
    public Messages(int id, String content,Timestamp messageTime, String fontStyle, String fontColor, String textBackground, String fontWeight, double fontSize, byte underLined,int sendAtHour, int sendAtMinute, int sendAtSecond) {

    this.content = content;
    this.id = id;
    this.fontColor= fontColor;
    this.fontStyle =fontStyle;
    this.textBackground = textBackground;
    this.fontWeight =fontWeight;
    this.fontSize = fontSize;
    this.underLined = underLined;
    this.messageTime = messageTime;

    }



    public void setId(int id) {
        this.id = id;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    public void setTextBackground(String textBackground) {
        this.textBackground = textBackground;
    }

    public void setUnderLined(byte underLined) {
        this.underLined = underLined;
    }

    /*---------------------------------------------------------------------------------------------------------*/

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }
    /*---------------------------------------------------------------------------------------------------------*/

    public byte getUnderLined() {
        return underLined;
    }

    public double getFontSize() {
        return fontSize;
    }

    public String getContent() {
        return content;
    }

    public String getFontColor() {
        return fontColor;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public String getTextBackground() {
        return textBackground;
    }
    public int getId() {
        return id;
    }

    public void setUser(User user) {
        this.user=user;
    }
    public User getUser() {
        return user;
    }

    public void setChat(Chat chat) {
        this.chat=chat;
    }
    public Chat getChat() {
        return chat;
    }



}