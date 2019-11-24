package cz.kamproductions.scp_wars.Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("../UI/Dialog-Base.fxml"));


            //URL url = new File("src/cz/kamproductions/scp_wars/Game/UI/Dialogs/Dialog-Base.fxml").toURI().toURL();
            //Scene scene = new Scene(loader.load(url), 800, 600);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/Dialogs/CustomDialog.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
