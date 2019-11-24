package cz.kamproductions.scp_wars;

import cz.kamproductions.scp_wars.Utilities.UTFControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SCPMain extends Application {
    //"cz.kamproductions.scp_wars.Game.UI.UI.fxml"

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource());
        //FXMLLoader.load("cz.kamproductions.scp_wars.Game.UI.UI.fxml");
        //FXMLLoader.getDefaultClassLoader().getResource("cz.kamproductions.scp_wars.Game.UI.UI.fxml");
        //Parent root = FXMLLoader.load(getClass().getResource("cz.kamproductions.scp_wars.Game.UI.UI.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Game/UI/UI.fxml"));


        URL url = new File("src/cz/kamproductions/scp_wars/Game/UI/UI.fxml").toURI().toURL();

        Locale localeEn = new Locale("en","US");
        Locale localeCz = new Locale("cz", "CZ");

        //ResourceBundle resourceBundleEn = ResourceBundle.getBundle("SCPWarsBundle", localeEn);
        //ResourceBundle resourceBundleCz = ResourceBundle.getBundle("SCPWarsBundle", localeCz);


        // UTF-8 Bundle
        ResourceBundle bundleCZUTF = ResourceBundle.getBundle("SCPWarsBundle", localeCz, new UTFControl());


        // Start in English
  //      Parent root = FXMLLoader.load(url, resourceBundleEn);

        // Start in Czech
        Parent root = FXMLLoader.load(url, bundleCZUTF);


        primaryStage.setTitle("SCP-Wars 0.0.1");
        primaryStage.setScene(new Scene(root, 1150, 820));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
