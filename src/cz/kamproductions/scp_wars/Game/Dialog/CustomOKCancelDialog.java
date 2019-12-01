package cz.kamproductions.scp_wars.Game.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomOKCancelDialog extends Stage implements Initializable {
    @FXML
    HBox buttonsHBox;

    @FXML
    TextArea DialogTextTextArea;

    @FXML
    Label DialogNameLabel;

    @FXML
    Button okButton, cancelButton;

    private boolean returnOK = false;

    public CustomOKCancelDialog(Parent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/Dialogs/CustomOKCancelDialog.fxml"));
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

        okButton.setOnAction(event -> {
            returnOK = true;
            this.close();
        });

        cancelButton.setOnAction(event -> {
            returnOK = false;
            this.close();
        });
    }


    public TextArea getDialogTextTextArea() {
        return DialogTextTextArea;
    }

    public void setDialogTextTextArea(TextArea dialogTextTextArea) {
        DialogTextTextArea = dialogTextTextArea;
    }

    public Label getDialogNameLabel() {
        return DialogNameLabel;
    }

    public void setDialogNameLabel(Label dialogNameLabel) {
        DialogNameLabel = dialogNameLabel;
    }

    public boolean showAndReturnValue() {
        super.showAndWait();
        return returnOK;
    }
}
