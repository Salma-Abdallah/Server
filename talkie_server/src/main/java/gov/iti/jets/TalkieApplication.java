package gov.iti.jets;

import gov.iti.jets.dto.requests.LoginRequest;
import gov.iti.jets.dto.requests.RegisterRequest;
import gov.iti.jets.dto.responses.LoginResponse;
import gov.iti.jets.dto.responses.RegisterResponse;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.impl.AuthenticationControllerSingleton;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.services.UserService;
import gov.iti.jets.utils.ImageUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Optional;

public class TalkieApplication extends Application{
    public static void main(String[] args) {

        NetworkManager.start();
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Home");
        Parent home = FXMLLoader.load(getClass().getResource("/views/server.fxml"));
        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.show();
    }
}