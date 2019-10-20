package cz.kamproductions.scp_wars.Game;

public class Employee {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    private String name;
    private EmployeeType employeeType;
    private Integer maintenanceCost;

    public Employee(String name, EmployeeType employeeType, Integer maintenanceCost) {
        this.name = name;
        this.employeeType = employeeType;
        this.maintenanceCost = maintenanceCost;
    }
}
