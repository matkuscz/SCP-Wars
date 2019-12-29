package cz.kamproductions.scp_wars.Game.UI.HumanResources;

import cz.kamproductions.scp_wars.Game.Employee;
import cz.kamproductions.scp_wars.Game.EmployeeType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class HumanResourcesBaseUI extends Stage implements Initializable {
    @FXML
    Button hireButton;

    @FXML
    Button dismissButton;

    @FXML
    TableView<Employee> employeeTableView;

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

        setupEmployeeTableView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hireButton.setDisable(true);
        dismissButton.setDisable(true);

        System.out.println("HR Controller init [OK]");
    }

    private void setupEmployeeTableView() {
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Employee, String> maintenanceCostColumn = new TableColumn<>("Maintenance cost");

//        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("type"));
//        maintenanceCostColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("maintenanceCost"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));


        employeeTableView.getColumns().clear();

        employeeTableView.getColumns().add(nameColumn);
        employeeTableView.getColumns().add(typeColumn);
        employeeTableView.getColumns().add(maintenanceCostColumn);
//        employeeTableView.getColumns().addAll(nameColumn, typeColumn, maintenanceCostColumn);




    }
}
