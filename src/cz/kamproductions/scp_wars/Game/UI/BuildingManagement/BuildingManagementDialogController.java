package cz.kamproductions.scp_wars.Game.UI.BuildingManagement;

import cz.kamproductions.scp_wars.Game.Building;
import cz.kamproductions.scp_wars.Game.Dialog.CustomDialogController;
import cz.kamproductions.scp_wars.Game.Dialog.CustomOKCancelDialog;
import cz.kamproductions.scp_wars.Game.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingManagementDialogController extends Stage implements Initializable {
    @FXML
    private BorderPane buildingManagementBorderPane;

    @FXML
    private Button closeButton;

    @FXML
    private Button rentButton;

    @FXML
    private Button buyButton;

    @FXML
    private Button marketReseachButton;

    private Building selectedBuildingOnMarket = null;


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
        rentButton.setDisable(true);
        setupBuyButton();


        //Tvoje soucasna budova - tabulka
        TableView<Building> currentBuildingTableView = CreateCurrentBuildingTableView();

        // Budovy ke koupi - tabulka
        TableView<Building> buildingsForSellTableView = CreateBuildingsForSellTableView();

        // Pridani seznamu budov do Layoutu
        VBox leftVbox = new VBox();
        VBox rightVBox = new VBox();

        leftVbox.setSpacing(10);
        rightVBox.setSpacing(10);

        leftVbox.setFillWidth(true);
        rightVBox.setFillWidth(false);

        Label currentBuildingLabel = new Label("Current building");
        leftVbox.getChildren().clear();
        leftVbox.getChildren().addAll(currentBuildingLabel, currentBuildingTableView);

        Label buildingsForSaleLabel = new Label("Buildings for sale");
        rightVBox.getChildren().clear();
        rightVBox.getChildren().addAll(buildingsForSaleLabel, buildingsForSellTableView);

        buildingManagementBorderPane.setLeft(leftVbox);
        buildingManagementBorderPane.setRight(rightVBox);

        buildingManagementBorderPane.setMinSize(1500, 900);

        //buildingManagementBorderPane.setCenter(null);

        buildingsForSellTableView.setMinWidth(650);
        currentBuildingTableView.setMinWidth(650);



        //Buy Button
        Tooltip buyButtonTooltip = new Tooltip("Buy selected building.You can move there later.");
        buyButton.setTooltip(buyButtonTooltip);

        //Rent Button
        Tooltip rentButtonTooltip = new Tooltip("Start renting this building.You can move there later.");
        rentButton.setTooltip(rentButtonTooltip);

        //Close Button
        closeButton.setOnAction(event -> {
            this.close();
        });
    }

    private ObservableList<Building> createBuildingsForSale() {
        ObservableList<Building> buildingsForSellList = FXCollections.observableArrayList();

        Building b1 = new Building(new String("Building A"), new String("Prvni budova, co je k mani..."), 50, 12000, 250000);
        Building b2 = new Building(new String("Building B"), new String("Druha budova. Je trochu vetsi..."), 80, 24000, 400000);

        buildingsForSellList.add(b1);
        buildingsForSellList.add(b2);

        return buildingsForSellList;
    }

    private ObservableList<Building> getCurrentPlayerBuildings() {
        ObservableList<Building> currentplayerBuildingsList = FXCollections.observableArrayList();
        currentplayerBuildingsList.addAll(Game.getGameInstance().getCorporation().getBuildings());

        return currentplayerBuildingsList;
    }

    private TableView<Building> CreateBuildingsForSellTableView() {
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

        buildingsForSellTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            if(newValue != null) {
                rentButton.setDisable(false);
                buyButton.setDisable(false);

                selectedBuildingOnMarket = newValue;
            } else {
                rentButton.setDisable(true);
                buyButton.setDisable(true);

                selectedBuildingOnMarket = null;
            }
        });

        return buildingsForSellTableView;
    }

    private TableView<Building> CreateCurrentBuildingTableView() {
        TableView<Building> currentBuildingTableView = new TableView<>();

        TableColumn<Building, String> nameCol = new TableColumn<>("Name");
        TableColumn<Building, String> descriptionCol = new TableColumn<>("Description");
        TableColumn<Building, Integer> areaCol = new TableColumn<>("Area (m2)");
        TableColumn<Building, Integer> yearRentPriceCol = new TableColumn<>("Yearly Rent Price $");
        TableColumn<Building, Integer> buyPriceCol = new TableColumn<>("Buy Price $");

        currentBuildingTableView .getColumns().clear();
        currentBuildingTableView .getColumns().addAll(nameCol, descriptionCol, areaCol, yearRentPriceCol, buyPriceCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<Building, String>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Building, String>("description"));
        areaCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("area"));
        yearRentPriceCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("yearRentPrice"));
        buyPriceCol.setCellValueFactory(new PropertyValueFactory<Building, Integer>("buyPrice"));

        ObservableList<Building> buildingsForSellList = getCurrentPlayerBuildings();
        currentBuildingTableView .setItems(buildingsForSellList);

        return currentBuildingTableView;
    }

    private void setupBuyButton() {
        buyButton.setDisable(true);

        buyButton.setOnAction(event -> {
            if(selectedBuildingOnMarket != null) {
                System.out.println("Buy button pressed");

//                CustomDialogController testBuyBuildingDialog = new CustomDialogController(null);
//                testBuyBuildingDialog.setTitle("Buy building");
//                testBuyBuildingDialog.getDialogNameLabel().setText("Buy building");
//                testBuyBuildingDialog.getDialogTextTextArea().setText(
//                        "Are you sure?\n" +
//                                "Costs: " + selectedBuildingOnMarket.getBuyPrice() + "\n" +
//                                "You current balance: " + Game.getGameInstance().getCorporation().getMoney() + " $$$.\n");
//
//                testBuyBuildingDialog.showAndWait();

                CustomOKCancelDialog testBuyDialog = new CustomOKCancelDialog(null);
                testBuyDialog.setTitle("Buy building");
                testBuyDialog.getDialogNameLabel().setText("Buy building");
                testBuyDialog.getDialogTextTextArea().setText(
                        "Are you sure?\n" +
                                "Costs: " + selectedBuildingOnMarket.getBuyPrice() + "\n" +
                                "You current balance: " + Game.getGameInstance().getCorporation().getMoney() + " $$$.\n");

                System.out.println("Vysledek: " + testBuyDialog.showAndReturnValue());
            }
        });
    }
}
