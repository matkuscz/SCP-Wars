package cz.kamproductions.scp_wars.Game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Facility {
    private StringProperty name;
    private IntegerProperty maintenanceCost;
    private IntegerProperty buildCost;
    private StringProperty description;
    private IntegerProperty requiredSpace;


    public Facility(String name, Integer maintenanceCost, Integer buildCost, String description, Integer requiredSpace) {
        this.name = new SimpleStringProperty(name);
        this.maintenanceCost = new SimpleIntegerProperty(maintenanceCost);
        this.buildCost = new SimpleIntegerProperty(buildCost);
        this.description = new SimpleStringProperty(description);
        this.requiredSpace = new SimpleIntegerProperty(requiredSpace);
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

    public int getBuildCost() {
        return buildCost.get();
    }

    public IntegerProperty buildCostProperty() {
        return buildCost;
    }

    public void setBuildCost(int buildCost) {
        this.buildCost.set(buildCost);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getRequiredSpace() {
        return requiredSpace.get();
    }

    public IntegerProperty requiredSpaceProperty() {
        return requiredSpace;
    }

    public void setRequiredSpace(int requiredSpace) {
        this.requiredSpace.set(requiredSpace);
    }
}
