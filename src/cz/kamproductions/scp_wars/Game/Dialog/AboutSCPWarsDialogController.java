package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AboutSCPWarsDialogController {
    @FXML
    private Button okButton;

    @FXML
    private void initialize() {
        okButton.setOnAction(event -> {
            Window window = okButton.getScene().getWindow();
            Stage stage = (Stage)window.getScene().getWindow();
            stage.close();
        });


    }
}
