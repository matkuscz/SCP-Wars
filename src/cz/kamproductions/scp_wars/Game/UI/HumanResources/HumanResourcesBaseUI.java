package cz.kamproductions.scp_wars.Game.UI.HumanResources;

import cz.kamproductions.scp_wars.Game.Employee;
import cz.kamproductions.scp_wars.Game.EmployeeType;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HumanResourcesBaseUI extends Stage implements Initializable {
    @FXML
    Button hireButton;

    @FXML
    Button dismissButton;

    @FXML
    TableView<Employee> employeesForHireTableView;

    @FXML
    TableView<Employee> currentEmployeesTableView;

    private ObservableList<Employee> employeesForHire;
    private ObservableList<Employee> currentEmployees;
    private Employee selectedEmployeeForHire = null;

    public HumanResourcesBaseUI(Parent parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/HumanResources/HumanResources-BaseUI.fxml"));
        loader.setController(this);

        try{
            Parent parentLoc = (Parent)loader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("HR INIT INIT [OK]");

        setupEmployeesForHireTableView();
        employeesForHireTableView.getItems().clear();
        employeesForHireTableView.setItems(createEmployeesForHire());

        setupCurrentEmployeesTableView();
        currentEmployeesTableView.getItems().clear();
        currentEmployeesTableView.setItems(createCurrentEmployeesObservableList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hireButton.setDisable(true);
        dismissButton.setDisable(true);

        System.out.println("HR Controller init [OK]");

        createEmployeesForHire();
    }

    private void setupEmployeesForHireTableView() {
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Employee, String> maintenanceCostColumn = new TableColumn<>("Maintenance cost");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeType"));
        maintenanceCostColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("maintenanceCost"));

        employeesForHireTableView.getColumns().clear();

        employeesForHireTableView.getColumns().add(nameColumn);
        employeesForHireTableView.getColumns().add(typeColumn);
        employeesForHireTableView.getColumns().add(maintenanceCostColumn);

        employeesForHireTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);

            if(newValue != null) {
                selectedEmployeeForHire = newValue;
                hireButton.setDisable(false);
            } else {
                selectedEmployeeForHire = null;
                hireButton.setDisable(true);
            }
        });

        hireButton.setOnAction(event -> {
            if(selectedEmployeeForHire != null) {
                Alert hireConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                hireConfirmAlert.setTitle("Hire new employee");
                hireConfirmAlert.setContentText("Really hire " + selectedEmployeeForHire.getName() +
                        " for " + selectedEmployeeForHire.getMaintenanceCost() + " $$$ per month?");



                Optional<ButtonType> result = hireConfirmAlert.showAndWait();
                if(result.isPresent() && result.get()==ButtonType.OK) {
                    System.out.println("Najimame typka");
                } else {
                    System.out.println("Zruseno najimani typka");
                }
            }
        });
    }

    private ObservableList<Employee> createEmployeesForHire() {
        employeesForHire = FXCollections.observableArrayList();
        Employee e1 = new Employee(EmployeeType.military, "Univerzalni vojak", 1000);
        Employee e2 = new Employee(EmployeeType.military, "Normalni vojak", 250);

        Employee e3 = new Employee(EmployeeType.science, "Silenej vedec", 6666);

        Employee e4 = new Employee(EmployeeType.generic_staff, "Uklizecka", 200);

        employeesForHire.addAll(e1, e2, e3, e4);

        return employeesForHire;
    }

    private void setupCurrentEmployeesTableView() {
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Employee, String> maintenanceCostColumn = new TableColumn<>("Maintenance cost");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeType"));
        maintenanceCostColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("maintenanceCost"));

        currentEmployeesTableView.getColumns().clear();

        currentEmployeesTableView.getColumns().add(nameColumn);
        currentEmployeesTableView.getColumns().add(typeColumn);
        currentEmployeesTableView.getColumns().add(maintenanceCostColumn);
    }

    private ObservableList<Employee> createCurrentEmployeesObservableList() {
        return currentEmployees = Game.getGameInstance().getCorporation().getEmployees();
    }
}
