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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FacilitiesManagementBaseController extends Stage implements Initializable {
    @FXML
    private Label totalFreeEstateLabel;
    @FXML
    private TableView<Facility> currentFacilitiesTableView;
    @FXML
    private Button buildButton;
    @FXML
    Button destroyFacilityButton;
    @FXML
    VBox estateDetailsVBox;

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

        totalFreeEstateLabel.setText(totalFreeSpace + " m2");

        setupCurrentFacilitiesTableView();

        buildButton.setOnAction(event -> {
            FacilitiesManagementBuildNewController facilitiesManagementBuildNewController = new FacilitiesManagementBuildNewController(null);
            facilitiesManagementBuildNewController.showAndWait();
        });

        calculateAndDisplayFreeRealEstate();

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

        currentFacilitiesTableView.setItems(Game.getGameInstance().getCorporation().getFacilities());

        destroyFacilityButton.setDisable(true);
    }

    private void calculateAndDisplayFreeRealEstate() {
        Label bHead = new Label("Buildings:");
        estateDetailsVBox.getChildren().add(bHead);

        int totalFreeEstate = 0;
        for(Building b : Game.getGameInstance().getCorporation().getBuildings()) {
            totalFreeEstate += b.getArea();

            Label bL = new Label("Building " + b.getName() + " - area " + b.getArea() + " m2");
            estateDetailsVBox.getChildren().add(bL);
        }

        int totalOccupiedFreeEstate = 0;
        Label facHead = new Label("Facilities:");
        estateDetailsVBox.getChildren().add(facHead);
        for(Facility f : Game.getGameInstance().getCorporation().getFacilities()) {
            totalOccupiedFreeEstate += f.getRequiredSpace();
            Label fL = new Label("Facility: " + f.getName() + " - area " + f.getRequiredSpace() + " m2");
            estateDetailsVBox.getChildren().add(fL);
        }

        int remainingFreeRealEstate = 0;
        remainingFreeRealEstate = totalFreeEstate - totalOccupiedFreeEstate;

        totalFreeEstateLabel.setText(remainingFreeRealEstate + " m2");
    }
}
