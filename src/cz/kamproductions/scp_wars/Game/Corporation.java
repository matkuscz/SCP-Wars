package cz.kamproductions.scp_wars.Game;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Corporation {
    private String name;
    private ArrayList<Facility> facilities = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private SimpleIntegerProperty moneyProp = new SimpleIntegerProperty(this, "money", 1000000);

    public Corporation(String name) {
        this.name = name;
    }

    public void processFinance() {
        Integer totalCosts = 0;

        for (Facility facility:facilities) {
            totalCosts += facility.getMaintenanceCost();
        }

        for (Employee employee:employees) {
            totalCosts += employee.getMaintenanceCost();
        }

        moneyPropProperty().setValue(getMoneyProp() - totalCosts);

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

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getMoneyProp() {
        return moneyProp.get();
    }

    public SimpleIntegerProperty moneyPropProperty() {
        return moneyProp;
    }

    public void setMoneyProp(int moneyProp) {
        this.moneyProp.set(moneyProp);
    }
}