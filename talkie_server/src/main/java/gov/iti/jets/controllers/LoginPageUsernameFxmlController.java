package gov.iti.jets.controllers;

import gov.iti.jets.models.CurrentAdmin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageUsernameFxmlController implements Initializable{

    @FXML
    private Label nextLabel;
    @FXML
    private TextField phoneNoTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        phoneNoTextField.setText(CurrentAdmin.getInstance().getAdmin().getPhoneNumber());
        nextLabel.setOnMouseClicked((MouseEvent event)-> {
            CurrentAdmin.getInstance().getAdmin().setPhoneNumber(phoneNoTextField.getText());
            Parent home = null;
            try {
                home = FXMLLoader.load(getClass().getResource("/views/login-page-password.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(home);
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
        });

    }

    public TextField getPhoneNoTextField() {
        return phoneNoTextField;
    }
}
