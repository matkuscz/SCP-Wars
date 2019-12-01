package cz.kamproductions.scp_wars.Game;

import cz.kamproductions.scp_wars.Game.Dialog.CustomDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Game {
    private Integer turn = 0;
    private Integer StartingYear = 2020;
    private Integer year = 0;
    private Corporation corporation;

    public Game(Player player) {
        createCorporation("KaM Studios");
        this.year = StartingYear;
        this.turn = 0;
    }

    public void processTurn(){
        corporation.processFinance();

        event();

        turn++;
        year++;

        System.out.println("Turn " + turn);
    }

    private void createCorporation(String name) {
        Corporation corporation = new Corporation(name);
        this.corporation = corporation;
    }

    public Integer getTurn() {
        return turn;
    }

    public Integer getYear() {
        return year;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    private void event() {
            CustomDialogController customDialogController = new CustomDialogController(null);
            customDialogController.showAndWait();
    }
}
