package cz.kamproductions.scp_wars.Game;

import cz.kamproductions.scp_wars.Game.Dialog.CustomDialogController;
import cz.kamproductions.scp_wars.Game.UI.Endgame.GameOverController;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private static Game gameInstance = null;
    private Integer StartingYear = 2020;
    private Integer year = 0;
    private Corporation corporation;
    private Player player;
    private SimpleIntegerProperty turn = new SimpleIntegerProperty(this, "turn", 0);
    private SimpleIntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    private Game() {
        createCorporation("KaM Studios");
        this.year = StartingYear;


    }

    public void processTurn(){
        corporation.processFinance();

        event();



        checkForEndgameCondition();
        showEndTurnSummary();


        year++;

        turn.setValue(getTurn() + 1);

        System.out.println("Turn " + getTurn());
    }

    private void createCorporation(String name) {
        Corporation corporation = new Corporation(name);


        this.corporation = corporation;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static Game getGameInstance() {
        if(gameInstance == null) {
            gameInstance = new Game();
        }

        return gameInstance;
    }

    public int getTurn() {
        return turn.get();
    }

    public SimpleIntegerProperty turnProperty() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn.set(turn);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    private void checkForEndgameCondition() {
        if(this.getCorporation().getMoney() < 0) {
            System.out.println("Game Over");

            GameOverController gameOverDialog = new GameOverController(null);
            gameOverDialog.showAndWait();
        }
    }

    private void showEndTurnSummary() {

    }
}
