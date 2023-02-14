package gov.iti.jets;

import gov.iti.jets.network.controllers.impl.BlockListControllerSingleton;
import gov.iti.jets.network.controllers.impl.UserProfileControllerSingleton;
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

        new Thread(()-> Application.launch()).start();


        System.out.println(BlockListControllerSingleton.getInstance());
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
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