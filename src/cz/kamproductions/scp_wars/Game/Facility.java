package cz.kamproductions.scp_wars.Game;

public class Facility {
    private String Name;
    private Integer MaintenanceCost;
    private String description;

    public Facility(String name, Integer maintenanceCost, String description) {
        this.Name = name;
        this.MaintenanceCost = maintenanceCost;
        this.description = description;
    }

    public Integer getMaintenanceCost() {
        return MaintenanceCost;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        MaintenanceCost = maintenanceCost;
    }
}
