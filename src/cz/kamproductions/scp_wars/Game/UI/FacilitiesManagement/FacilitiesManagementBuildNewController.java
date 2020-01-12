package cz.kamproductions.scp_wars.Game.UI.FacilitiesManagement;

import cz.kamproductions.scp_wars.Game.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FacilitiesManagementBuildNewController extends Stage implements Initializable {
    @FXML
    private Label freeRealEstateLabel;

    @FXML
    private Label fundsLabel;

    @FXML
    private TableView<Facility> buildFacilityTableView;

    @FXML
    private Button moreInfoButton;

    @FXML
    private Button buyButton;

    @FXML
    private Button closeButton;

    private Facility selectedFacility = null;

    public FacilitiesManagementBuildNewController(Parent parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/FacilitiesManagement/FacilitiesManagement-BuildNew.fxml"));
        loader.setController(this);

        try{
            Parent parentLoc = (Parent)loader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Facilities-Build LOAD [OK]");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupBuildFacilitiesTableView();

        buyButton.setDisable(true);
        moreInfoButton.setDisable(true);

        closeButton.setOnAction(event -> {
            this.close();
        });

        Integer money = Game.getGameInstance().getCorporation().getMoney();
        fundsLabel.setText(money.toString());

        Integer freeRealEstate = 0;
        for(Building b : Game.getGameInstance().getCorporation().getBuildings()) {
            freeRealEstate += b.getArea();
        }

        freeRealEstateLabel.setText(freeRealEstate.toString());

        setupBuyAndInfoButtons();

        System.out.println("Facilities-Build INIT [OK]");
    }

    private ObservableList<Facility> createFacilitiesToBuild() {
        ObservableList<Facility> facilities = FXCollections.observableArrayList();

        Laboratory lab = new Laboratory("Laborka", 5000, 50000, "Basic small lab.Provides research.", 25);
        Armory armory = new Armory("Zbrojirna", 2000, 10000, "Small armory. You can equip your soldiers here.", 50);

        facilities.add(lab);
        facilities.add(armory);

        return facilities;
    }

    private void setupBuildFacilitiesTableView() {
        TableColumn<Facility, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Facility, String> maintenanceCostColumn = new TableColumn<>("Maintenance cost");
        TableColumn<Facility, String> buildCostColumn = new TableColumn<>("Build cost");
        TableColumn<Facility, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Facility, String> requiredSpaceColumn = new TableColumn<>("Required space");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Facility, String>("name"));
        maintenanceCostColumn.setCellValueFactory(new PropertyValueFactory<Facility, String>("maintenanceCost"));
        buildCostColumn.setCellValueFactory(new PropertyValueFactory<Facility, String>("buildCost"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Facility, String>("description"));
        requiredSpaceColumn.setCellValueFactory(new PropertyValueFactory<Facility, String>("requiredSpace"));

        buildFacilityTableView.getColumns().clear();

        buildFacilityTableView.getColumns().add(nameColumn);
        buildFacilityTableView.getColumns().add(maintenanceCostColumn);
        buildFacilityTableView.getColumns().add(buildCostColumn);
        buildFacilityTableView.getColumns().add(requiredSpaceColumn);
        buildFacilityTableView.getColumns().add(descriptionColumn);

        buildFacilityTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);

            if(newValue != null) {
                selectedFacility = newValue;

                int potentionalRemainingMoney = Game.getGameInstance().getCorporation().getMoney() - selectedFacility.getBuildCost();

                int totalSpace = 0;
                for(Building b : Game.getGameInstance().getCorporation().getBuildings()) {
                    totalSpace += b.getArea();
                }

                int totalFacilitiesSize = 0;
                for(Facility f : Game.getGameInstance().getCorporation().getFacilities()) {
                    totalFacilitiesSize += f.getRequiredSpace();
                }

                int potentionalRemainingSpace = totalSpace - (totalFacilitiesSize + selectedFacility.getRequiredSpace());

                if(potentionalRemainingMoney >= 0 && potentionalRemainingSpace >= 0) {
                    buyButton.setDisable(false);
                } else {
                    buyButton.setDisable(true);
                }

                moreInfoButton.setDisable(false);
            } else {
                selectedFacility = null;
                buyButton.setDisable(true);
                moreInfoButton.setDisable(true);
            }
        });

        buildFacilityTableView.getItems().clear();
        buildFacilityTableView.getItems().addAll(createFacilitiesToBuild());
    }

    private void setupBuyAndInfoButtons() {
        buyButton.setOnAction(event -> {
            if(selectedFacility != null) {
                int potentionalRemainingMoney = Game.getGameInstance().getCorporation().getMoney() - selectedFacility.getBuildCost();

                Alert buyFacilityConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                buyFacilityConfirmationAlert.setTitle("Build new facility");
                buyFacilityConfirmationAlert.setContentText("Are you sure that you want build " +
                        selectedFacility.getName() + " for " + selectedFacility.getBuildCost() + " $$$ ?\n" +
                        "You will end with " + potentionalRemainingMoney + " $$$.");

                Optional<ButtonType> result = buyFacilityConfirmationAlert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("Kupujeme facilitku (" + selectedFacility.getName() + ").");

                    Game.getGameInstance().getCorporation().setMoney(potentionalRemainingMoney);
                    Game.getGameInstance().getCorporation().getFacilities().add(selectedFacility);
                } else {
                    System.out.println("Kupovani facilitky cancelled.");
                }
            }
        });

        moreInfoButton.setOnAction(event -> {
            if(selectedFacility != null) {
                int potentionalRemainingMoney = Game.getGameInstance().getCorporation().getMoney() - selectedFacility.getBuildCost();

                int totalSpace = 0;
                for(Building b : Game.getGameInstance().getCorporation().getBuildings()) {
                    totalSpace += b.getArea();
                }

                int totalFacilitiesSize = 0;
                for(Facility f : Game.getGameInstance().getCorporation().getFacilities()) {
                    totalFacilitiesSize += f.getRequiredSpace();
                }

                int potentionalRemainingSpace = totalSpace - (totalFacilitiesSize + selectedFacility.getRequiredSpace());

                StringBuilder infoTextBuilder = new StringBuilder();

                infoTextBuilder.append("Facility: " + selectedFacility.getName() + "\n" +
                        "Description:\n" + selectedFacility.getDescription() + "\n\n\n" +
                        "Construction cost ($$$): " + selectedFacility.getBuildCost() + "\n" +
                        "Maintenance cost ($$$): " + selectedFacility.getMaintenanceCost() + "\n" +
                        "Required space (m2): " + selectedFacility.getRequiredSpace() + "\n");

                if(potentionalRemainingMoney < 0) {
                    infoTextBuilder.append("\n\nYou cannot build this facility because you don't have enough money.");
                }

                if(potentionalRemainingSpace < 0) {
                    infoTextBuilder.append("\n\nYou cannot build this facility because you don't have enough space in your buildings.");
                }

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Info about facility");
                infoAlert.setContentText(infoTextBuilder.toString());

                infoAlert.showAndWait();
            }

        });
    }
}
