package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutGameCustomDialog extends Stage implements Initializable {
    @FXML
    private Button okButton;

    @FXML
    private TextArea text;

    public AboutGameCustomDialog(Parent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/Dialogs/AboutSCPWarsDialog.fxml"));
        fxmlLoader.setController(this);

        try{
            Parent parentLoc = (Parent)fxmlLoader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okButton.setOnAction(event -> {
            System.out.println("aaaaa");
            this.close();
        });

        text.setEditable(false);
    }


}
