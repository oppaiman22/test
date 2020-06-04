package tictactoe.model;

public class GameProperty {
    
    private final int PORT = 5555;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int FIELD_WIDTH = WIDTH / 3;
    private final int FIELD_HEIGHT = HEIGHT / 3;
    private final int NOBODY = 0;
    private final int PLAYER_ONE = 1;
    private final int PLAYER_TWO = 2;

    public int getPORT() {
        return PORT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getFIELD_WIDTH() {
        return FIELD_WIDTH;
    }

    public int getFIELD_HEIGHT() {
        return FIELD_HEIGHT;
    }
    
    public int getNOBODY() {
        return NOBODY;
    }

    public int getPLAYER_ONE() {
        return PLAYER_ONE;
    }

    public int getPLAYER_TWO() {
        return PLAYER_TWO;
    }
}
