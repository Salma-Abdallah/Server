package gov.iti.jets.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TextController implements Initializable {

    @FXML
    private Text msg;

    private String msgStr;
    public TextController(String msgTxt) {
        this.msgStr = msgTxt;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msg.setText(msgStr);
    }

}