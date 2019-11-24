package cz.kamproductions.scp_wars.Game.UI.BuildingManagement;

import cz.kamproductions.scp_wars.Game.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingManagementDialogController extends Stage implements Initializable {
    //@FXML
    //private TableView currentBuildingTableView;

    @FXML
    private BorderPane buildingManagementBorderPane;

    @FXML
    private Button closeButton;

    public BuildingManagementDialogController(Parent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/BuildingManagement/BuildingManagement-Base.fxml"));
        fxmlLoader.setController(this);

        try{
            Parent parentLoc = (Parent)fxmlLoader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        // Prvni radek do tabulky - HEADER
//        Label headerLabel = new Label("Your Building");
//        VBox headerVBox = new VBox();
//
//        headerVBox.getChildren().add(headerLabel);
//
//        headerVBox.setAlignment(Pos.CENTER);
//
//        //headerVBox.getChildren().add(headerVBox);
//        //currentBuildingTableView
//
//        TableColumn<String, String> tableColumnHeader = new TableColumn();
//        tableColumnHeader.setGraphic(headerVBox);
//
//        TableColumn<Building, String> tableColumnName = new TableColumn<Building, String>("Name");
//        //tableColumnName.setCellFactory();



        //Moje budova - tabulka
        TableView<Building> currentBuildingTableView = new TableView<>();


        // Budovy ke koupi - tabulka
        TableView<Building> buildingsForSellTableView = new TableView<>();

        TableColumn<Building, String> nameCol = new TableColumn<>("Name");
        TableColumn<Building, String> descriptionCol = new TableColumn<>("Description");
        TableColumn<Building, Integer> areaCol = new TableColumn<>("Area (m2)");
        TableColumn<Building, Integer> yearRentPriceCol = new TableColumn<>("Yearly Rent Price $");
        TableColumn<Building, Integer> buyPriceCol = new TableColumn<>("Buy Price $");

        buildingsForSellTableView.getColumns().clear();
        buildingsForSellTableView.getColumns().addAll(nameCol, descriptionCol, areaCol, yearRentPriceCol, buyPriceCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<Building, String>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Building, String>("description"));
        areaCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("area"));
        yearRentPriceCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("yearRentPrice"));
        buyPriceCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("buyPrice"));


        ObservableList<Building> buildingsForSellList = createBuildingsForSale();
        buildingsForSellTableView.setItems(buildingsForSellList);


        buildingManagementBorderPane.setRight(buildingsForSellTableView);
    }

    private ObservableList<Building> createBuildingsForSale() {
        ObservableList<Building> buildingsForSellList = FXCollections.observableArrayList();

        Building b1 = new Building(new String("Building A"), new String("Prvni budova, co je k mani..."), 50, 12000, 250000);
        Building b2 = new Building(new String("Building B"), new String("Druha budova. Je trochu vetsi..."), 80, 24000, 400000);

        buildingsForSellList.add(b1);
        buildingsForSellList.add(b2);

        return buildingsForSellList;
    }
}
