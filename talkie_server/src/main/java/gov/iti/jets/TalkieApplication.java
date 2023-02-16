package gov.iti.jets;

import gov.iti.jets.network.controllers.impl.BlockListControllerSingleton;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
        Parent home = FXMLLoader.load(getClass().getResource("/views/login-page-username.fxml"));
        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}