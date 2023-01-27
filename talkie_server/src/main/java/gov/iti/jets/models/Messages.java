package gov.iti.jets.models;

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


    private int sendAtHour;
    private int sendAtMinute;
    private int sendAtSecond;
    /*------------------------------------------------------------------------------------------*/
    public Messages(int id, String content, String fontStyle, String fontColor, String textBackground, String fontWeight, double fontSize, byte underLined,int sendAtHour, int sendAtMinute, int sendAtSecond) {
    chat.getId();
    user.getId();
    this.content = content;
    this.id = id;
    this.fontColor= fontColor;
    this.fontStyle =fontStyle;
    this.textBackground = textBackground;
    this.fontWeight =fontWeight;
    this.fontSize = fontSize;
    this.underLined = underLined;
    this.sendAtHour = sendAtHour;
    this.sendAtMinute = sendAtMinute;
    this.sendAtSecond= sendAtSecond;

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

    public void setTimehour(int hour){
        sendAtHour = ((hour>=0 && hour<24) ? hour:0);
    }
    public void setTimeMinite(int minute) {
        sendAtMinute = ((minute>=0 && minute<60)? minute:0);
    }
    public void setTimeSecond(int second) {

        sendAtSecond = ((second >= 0 && second < 60) ? second : 0);
    }
    public String tosrtringHour(){
        return String.format("%2d", sendAtHour);
    }
    public String tosrtringMinites() {
        return String.format("%2d", sendAtMinute);
    }
    public String tosrtringSecond() {
        return String.format("%2d", sendAtSecond);
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


}