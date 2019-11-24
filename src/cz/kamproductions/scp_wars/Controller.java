package cz.kamproductions.scp_wars;

import cz.kamproductions.scp_wars.Game.Dialog.AboutGameCustomDialog;
import cz.kamproductions.scp_wars.Game.Game;
import cz.kamproductions.scp_wars.Game.Player;
import cz.kamproductions.scp_wars.Game.UI.BuildingManagement.BuildingManagementDialogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    Player player = new Player("MTK");
    Game game = new Game(player);

    @FXML
    Button nextTurnButton;
    @FXML
    Label turn_value;
    @FXML
    Label corporation_name;
    @FXML
    Label balance_value;
    @FXML
    MenuItem aboutMenuItem;
    @FXML
    Label year_value;
    @FXML
    Button buildingManagementButton;

    private ResourceBundle bundle;

    @FXML
    private void initialize(URL location, ResourceBundle resources) {
        bundle = resources;

        aboutMenuItem.setOnAction(event -> {
            AboutGameCustomDialog aboutGameCustomDialog = new AboutGameCustomDialog(null);
            aboutGameCustomDialog.showAndWait();
        });


        nextTurnButton.setOnAction(event -> {
            game.processTurn();
            render();
        });

        buildingManagementButton.setOnAction(event -> {
            BuildingManagementDialogController buildingManagementDialog = new BuildingManagementDialogController(null);
            buildingManagementDialog.showAndWait();
        });



        render();
    }

    private void render() {
        turn_value.setText(game.getTurn().toString());
        corporation_name.setText(game.getCorporation().getName());
        balance_value.setText(game.getCorporation().getMoney().toString());
        year_value.setText(game.getYear().toString());
    }
}
