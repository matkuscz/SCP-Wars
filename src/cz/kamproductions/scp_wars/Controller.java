package cz.kamproductions.scp_wars;

import cz.kamproductions.scp_wars.Game.*;
import cz.kamproductions.scp_wars.Game.Dialog.AboutGameCustomDialog;
import cz.kamproductions.scp_wars.Game.UI.BuildingManagement.BuildingManagementDialogController;
import cz.kamproductions.scp_wars.Game.UI.HumanResources.HumanResourcesBaseUI;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    @FXML
    Button humanResourcesButton;
    @FXML
    Button researchButton;
    @FXML
    StackPane researchButtonStackPane;

    private ResourceBundle bundle;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controller init started ...");

        Player player = new Player("MTK");

        Game game = Game.getGameInstance();
        game.setPlayer(player);
        game.getCorporation().setBuildings(createDefaultBuildings());

        bundle = resources;

        aboutMenuItem.setOnAction(event -> {
            AboutGameCustomDialog aboutGameCustomDialog = new AboutGameCustomDialog(null);
            aboutGameCustomDialog.initModality(Modality.APPLICATION_MODAL);

            aboutGameCustomDialog.showAndWait();
        });

        nextTurnButton.setOnAction(event -> {
            Game.getGameInstance().processTurn();
            render();
        });

        buildingManagementButton.setOnAction(event -> {
            BuildingManagementDialogController buildingManagementDialog = new BuildingManagementDialogController(null);
            buildingManagementDialog.initModality(Modality.APPLICATION_MODAL);

            buildingManagementDialog.showAndWait();
        });

        buildingManagementButton.setTooltip(new Tooltip("Rent/Buy/Sale buildings for your corporation..."));

        humanResourcesButton.setOnAction(event -> {
            HumanResourcesBaseUI humanResourcesBaseUI = new HumanResourcesBaseUI(null);
            humanResourcesBaseUI.initModality(Modality.APPLICATION_MODAL);

            humanResourcesBaseUI.showAndWait();
        });

        setupResearchButton();

        render();

        System.out.println("Controller init [OK]");

        balance_value.textProperty().bind(Bindings.convert(Game.getGameInstance().getCorporation().moneyProperty()));
        turn_value.textProperty().bind(Bindings.convert(Game.getGameInstance().turnProperty()));
    }

    private void render() {
        corporation_name.setText(Game.getGameInstance().getCorporation().getName());
        year_value.setText(Game.getGameInstance().getYear().toString());
    }

    private ObservableList<Building> createDefaultBuildings() {
        Building b1 = new Building("Nic", "Zadna budova", 0, 0, 0);
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(b1);

//        return buildings;

        return FXCollections.observableArrayList(buildings);
    }

    private void setupResearchButton() {
        //researchButton.setDisable(true);
        researchButton.setTooltip(new Tooltip("You don't have any scientists..."));

        researchButton.setOnAction(event -> {
            ObservableList<Employee> seznam = Game.getGameInstance().getCorporation().getEmployees();

            boolean doWeHaveScientist = false;

            for(Employee e: seznam) {
                if(e.getEmployeeType().equals(EmployeeType.science)) {
                    doWeHaveScientist = true;
                }
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);

            if(doWeHaveScientist) {
                alert.setTitle("Research ready to proceeed...");
                alert.setContentText("Research ready to proceeed...");
            } else {
                alert.setTitle("You do not have any scientist...");
                alert.setContentText("You do not have any scientist...");
            }

            alert.showAndWait();


        });
    }
}
