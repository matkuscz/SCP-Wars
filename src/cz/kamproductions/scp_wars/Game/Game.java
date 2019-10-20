package cz.kamproductions.scp_wars.Game;

public class Game {
    private Integer turn = 0;
    private Corporation corporation;

    public Game(Player player) {
        createCorporation("KaM Studios");
    }

    public void processTurn(){
        corporation.processFinance();
        turn++;

        System.out.println("Turn " + turn);
    }

    private void createCorporation(String name) {
        Corporation corporation = new Corporation(name);
        this.corporation = corporation;
    }

    public Integer getTurn() {
        return turn;
    }

    public Corporation getCorporation() {
        return corporation;
    }
}
