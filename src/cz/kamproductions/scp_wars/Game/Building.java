package cz.kamproductions.scp_wars.Game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Building {
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty area;
    private IntegerProperty yearRentPrice;
    private IntegerProperty buyPrice;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public int getArea() {
        return area.get();
    }

    public IntegerProperty areaProperty() {
        return area;
    }

    public void setArea(int area) {
        this.area.set(area);
    }

    public int getYearRentPrice() {
        return yearRentPrice.get();
    }

    public IntegerProperty yearRentPriceProperty() {
        return yearRentPrice;
    }

    public void setYearRentPrice(int yearRentPrice) {
        this.yearRentPrice.set(yearRentPrice);
    }

    public int getBuyPrice() {
        return buyPrice.get();
    }

    public IntegerProperty buyPriceProperty() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice.set(buyPrice);
    }


//    public Building(String name, String description, Integer area, Integer yearRentPrice, Integer buyPrice) {
//        this.setName(name);
//        this.setDescription(description);
//        this.setArea(area);
//        this.setYearRentPrice(yearRentPrice);
//        this.setBuyPrice(buyPrice);
//    }


    public Building(String name, String description, Integer area, Integer yearRentPrice, Integer buyPrice) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.area = new SimpleIntegerProperty(area);
        this.yearRentPrice = new SimpleIntegerProperty(yearRentPrice);
        this.buyPrice = new SimpleIntegerProperty(buyPrice);
    }
}
