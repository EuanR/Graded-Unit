package com.simplyrugby;

import com.simplyrugby.controllers.LoginController;
import com.simplyrugby.modals.Modal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Euan
 * Project docs can be found at https://docs.gradedunit.euanriggans.com
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates the Login view
     *
     * @param primaryStage Stage for Login view
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        Modal modal = new Modal();
        controller.setModal(modal);
        primaryStage.setTitle("Simply Rugby Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        File tempIconLocation = new File("resources/sru_logo.png");
        Image icon = new Image("file:\\" + tempIconLocation.getCanonicalPath());
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}
