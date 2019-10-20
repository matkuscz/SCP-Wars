package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DialogController {
    @FXML
    ImageView image;

    @FXML
    TextFlow otazka_TextFlow;

    @FXML
    VBox odpovedi_vbox;

    @FXML
    private void initialize() {
        otazka_TextFlow.getChildren().add(new Text("Testujici otazka"));

        Text odpoved1 = new Text("Odpoved 1");
        Text odpoved2 = new Text("Odpoved 2");

        odpovedi_vbox.getChildren().add(odpoved1);
        odpovedi_vbox.getChildren().add(odpoved2);
    }
}
