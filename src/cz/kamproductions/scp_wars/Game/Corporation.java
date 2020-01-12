package cz.kamproductions.scp_wars.Game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Corporation {
    private String name;

    private ObservableList<Facility> facilities;
    private ObservableList<Building> buildings;
    private ObservableList<Employee> employees;
    private SimpleIntegerProperty money = new SimpleIntegerProperty(this, "money", 1000000);

    public Corporation(String name) {
        this.name = name;
        ArrayList<Building> buildingsArray = new ArrayList<>();
        buildings = FXCollections.observableArrayList(buildingsArray);

        ArrayList<Employee> employeesArray = new ArrayList<>();
        employees = FXCollections.observableArrayList(employeesArray);
        Employee ceo = new Employee(EmployeeType.ceo, "Matkus", 1);
        employees.add(ceo);

        ArrayList<Facility> facilitiesArray = new ArrayList<>();
        facilities = FXCollections.observableArrayList(facilitiesArray);
    }

    public void processFinance() {
        Integer totalCosts = 0;

        for (Facility facility:facilities) {
            totalCosts += facility.getMaintenanceCost();
        }

        for (Employee employee:employees) {
            totalCosts += employee.getMaintenanceCost();
        }

        // Increase score by every cent above zero total worth
        Game.getGameInstance().setScore(Game.getGameInstance().getScore() + totalCosts);

        moneyProperty().setValue(getMoney() - totalCosts);

        System.out.println("Total costs: " + totalCosts);
    }

    private void buildFacility(Facility facility) {
        this.facilities.add(facility);
    }

    private void hireEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public String getName() {
        return name;
    }

//    public ArrayList<Building> getBuildings() {
//        return buildings;
//    }
//
//    public void setBuildings(ArrayList<Building> buildings) {
//        this.buildings = buildings;
//    }


    public ObservableList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ObservableList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getMoney() {
        return money.get();
    }

    public SimpleIntegerProperty moneyProperty() {
        return money;
    }

    public void setMoney(int money) {
        this.money.set(money);
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ObservableList<Employee> employees) {
        this.employees = employees;
    }

    public ObservableList<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(ObservableList<Facility> facilities) {
        this.facilities = facilities;
    }
}