package gov.iti.jets.controllers;

import gov.iti.jets.models.User;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    TextController txtCon;
    @FXML
    private HBox hBox;
    @FXML
    private ImageView power;
    @FXML
    private TextField msgTxt;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/charts.fxml"));
        try {
            hBox.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void power(MouseEvent mouseEvent) {
        NetworkManager networkManager = new NetworkManager();
        if (power.getImage().getUrl().equals(new Image("/images/shutdown.png").getUrl())) {
            networkManager.stop();
            power.setImage(new Image("/images/openServer.png"));
        } else if (power.getImage().getUrl().equals(new Image("/images/openServer.png").getUrl())) {
            networkManager.start();
            power.setImage(new Image("/images/shutdown.png"));
        }
    }

    public void sendMsg(ActionEvent actionEvent) {
        if (msgTxt.getText() == null || msgTxt.getText().trim().equals(""))
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/text.fxml"));
        txtCon = new TextController(msgTxt.getText());
        loader.setController(txtCon);
        try {
            vBox.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scroll.vvalueProperty().bind(vBox.heightProperty());
        msgTxt.clear();
//            serverImp.tellOthers(msgTxt);
    }
}
