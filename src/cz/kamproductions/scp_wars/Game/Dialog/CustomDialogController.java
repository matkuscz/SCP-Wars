package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
