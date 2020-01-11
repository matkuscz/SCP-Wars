package cz.kamproductions.scp_wars.Game;

import javafx.beans.property.*;

public class Employee {
    private SimpleObjectProperty<EmployeeType> employeeType;
    private StringProperty name;
    private IntegerProperty maintenanceCost;

    public Employee(EmployeeType employeeType, String name, Integer maintenanceCost) {
        this.employeeType = new SimpleObjectProperty<EmployeeType>(employeeType);
        this.name = new SimpleStringProperty(name);
        this.maintenanceCost = new SimpleIntegerProperty(maintenanceCost);
    }

    public EmployeeType getEmployeeType() {
        return employeeType.get();
    }

    public SimpleObjectProperty<EmployeeType> employeeTypeProperty() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType.set(employeeType);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getMaintenanceCost() {
        return maintenanceCost.get();
    }

    public IntegerProperty maintenanceCostProperty() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(int maintenanceCost) {
        this.maintenanceCost.set(maintenanceCost);
    }

    //    public Employee(String name, EmployeeType employeeType, Integer maintenanceCost) {
//        nameProperty.set(name);
//        employeeTypeProperty.set(employeeType);
//        maintenanceCostProperty.set(maintenanceCost);
//    }

//    public EmployeeType getEmployeeTypeProperty() {
//        return employeeTypeProperty.get();
//    }
//
//    public SimpleObjectProperty<EmployeeType> employeeTypePropertyProperty() {
//        return employeeTypeProperty;
//    }
//
//    public void setEmployeeTypeProperty(EmployeeType employeeTypeProperty) {
//        this.employeeTypeProperty.set(employeeTypeProperty);
//    }
//
//    public String getNameProperty() {
//        return nameProperty.get();
//    }
//
//    public StringProperty namePropertyProperty() {
//        return nameProperty;
//    }
//
//    public void setNameProperty(String nameProperty) {
//        this.nameProperty.set(nameProperty);
//    }
//
//    public int getMaintenanceCostProperty() {
//        return maintenanceCostProperty.get();
//    }
//
//    public IntegerProperty maintenanceCostPropertyProperty() {
//        return maintenanceCostProperty;
//    }
//
//    public void setMaintenanceCostProperty(int maintenanceCostProperty) {
//        this.maintenanceCostProperty.set(maintenanceCostProperty);
//    }
}
