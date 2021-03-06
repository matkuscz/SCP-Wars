package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomDialogController extends Stage implements Initializable {
    @FXML
    private Label DialogNameLabel;

    @FXML
    private TextArea DialogTextTextArea;

    @FXML
    private Button okButton;


    public CustomDialogController(Parent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/Dialogs/CustomDialog.fxml"));
        fxmlLoader.setController(this);

        try{
            Parent parentLocation = (Parent)fxmlLoader.load();
            Scene scene = new Scene(parentLocation);
            setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DialogTextTextArea.setEditable(false);

        DialogTextTextArea.setText("QWEASDZXCCVBDFGERTTYGHVB");


        okButton.setOnAction(event -> {
            this.close();
        });
    }

    public Label getDialogNameLabel() {
        return DialogNameLabel;
    }

    public void setDialogNameLabel(Label dialogNameLabel) {
        DialogNameLabel = dialogNameLabel;
    }

    public TextArea getDialogTextTextArea() {
        return DialogTextTextArea;
    }

    public void setDialogTextTextArea(TextArea dialogTextTextArea) {
        DialogTextTextArea = dialogTextTextArea;
    }
}
