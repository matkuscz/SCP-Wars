package cz.kamproductions.scp_wars;

import cz.kamproductions.scp_wars.Game.Game;
import cz.kamproductions.scp_wars.Game.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    Player player = new Player("MTK");
    Game game = new Game(player);

    @FXML
    Button nextTurnButton;
    @FXML
    Label turn_value;
    @FXML
    Label corporation_name;
    @FXML
    Label balance_value;

    @FXML
    private void initialize() {
        nextTurnButton.setOnAction(event -> {
            game.processTurn();
            render();
        });

        render();
    }

    private void render() {
        turn_value.setText(game.getTurn().toString());
        corporation_name.setText(game.getCorporation().getName());
        balance_value.setText(game.getCorporation().getMoney().toString());
    }
}
