package tictactoe.model;

import java.io.Serializable;

public class GameEndPacket implements Serializable {

    private int winner;

    public GameEndPacket(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}
