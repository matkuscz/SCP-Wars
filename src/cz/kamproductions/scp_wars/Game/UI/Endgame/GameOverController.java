package cz.kamproductions.scp_wars.Game.UI.Endgame;

import cz.kamproductions.scp_wars.Game.Employee;
import cz.kamproductions.scp_wars.Game.EmployeeType;
import cz.kamproductions.scp_wars.Game.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController extends Stage implements Initializable {
    @FXML
    private TextArea gameOverTextArea;

    public GameOverController(Parent parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/Endgame/GameOver-Base.fxml"));
        loader.setController(this);

        try{
            Parent parentLoc = (Parent)loader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("GameOver INIT [OK]");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game instance = Game.getGameInstance();
        String ceoName = "UNKNOWN";
        for(Employee e : instance.getCorporation().getEmployees()) {
            if(e.getEmployeeType().equals(EmployeeType.ceo)) {
                ceoName = e.getName();
            }
        }

        gameOverTextArea.setText("Congratulations,\n" +
                "Your corporation " + instance.getCorporation().getName() +
                        " under CEO " + ceoName
                        + " survived for " + instance.getTurn() + " turns, to year " + instance.getYear() +" AD.\n" +
                        "Remaining funds: " + instance.getCorporation().getMoney() + " $$$.\n" +
                        "Final score: " + instance.getScore() + "\n");
    }
}
