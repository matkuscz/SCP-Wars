package cz.kamproductions.scp_wars.Game;

public class Facility {
    private String Name;
    private Integer MaintenanceCost;

    public Facility(String name, Integer maintenanceCost) {
        Name = name;
        MaintenanceCost = maintenanceCost;
    }

    public Integer getMaintenanceCost() {
        return MaintenanceCost;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        MaintenanceCost = maintenanceCost;
    }
}
