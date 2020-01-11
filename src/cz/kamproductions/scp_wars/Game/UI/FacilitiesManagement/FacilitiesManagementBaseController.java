package cz.kamproductions.scp_wars.Game.UI.FacilitiesManagement;

import cz.kamproductions.scp_wars.Game.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FacilitiesManagementBaseController extends Stage implements Initializable {
    @FXML
    private Label freeEstateSpaceLabel;
    @FXML
    private TableView<Facility> currentFacilitiesTableView;

    @FXML
    Button destroyFacilityButton;

    private Facility selectedFacility;

    public FacilitiesManagementBaseController(Parent parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/FacilitiesManagement/FacilitiesManagement-BaseUI.fxml"));
        loader.setController(this);

        try{
            Parent parentLoc = (Parent)loader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Facilities LOAD [OK]");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int totalFreeSpace = 0;
        for(Building b : Game.getGameInstance().getCorporation().getBuildings()) {
            totalFreeSpace += b.getArea();
        }

        freeEstateSpaceLabel.setText(totalFreeSpace + " m2");

        setupCurrentFacilitiesTableView();

        System.out.println("Facilities INIT [OK]");
    }

    private void setupCurrentFacilitiesTableView() {
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

        currentFacilitiesTableView.getColumns().clear();

        currentFacilitiesTableView.getColumns().add(nameColumn);
        currentFacilitiesTableView.getColumns().add(maintenanceCostColumn);
        currentFacilitiesTableView.getColumns().add(buildCostColumn);
        currentFacilitiesTableView.getColumns().add(descriptionColumn);
        currentFacilitiesTableView.getColumns().add(requiredSpaceColumn);

        currentFacilitiesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);

            if(newValue != null) {
                selectedFacility = newValue;
                destroyFacilityButton.setDisable(false);
            } else {
                selectedFacility = null;
                destroyFacilityButton.setDisable(true);
            }
        });

        currentFacilitiesTableView.getItems().clear();
        currentFacilitiesTableView.getItems().addAll(createFacilitiesToBuild());

        destroyFacilityButton.setDisable(true);
    }

    private ObservableList<Facility> createFacilitiesToBuild() {
        ObservableList<Facility> facilities = FXCollections.observableArrayList();

        Laboratory lab = new Laboratory("Laborka", 5000, 50000, "Basic small lab.Provides research.", 25);
        Armory armory = new Armory("Zbrojirna", 2000, 10000, "Small armory. You can equip your soldiers here.", 50);

        facilities.add(lab);
        facilities.add(armory);

        return facilities;
    }
}
