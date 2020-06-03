package tictactoe.model;

public class GameProperty {
    
    private int PORT = 5555;
    private int WIDTH = 600;
    private int HEIGHT = 600;
    private int FIELD_WIDTH = WIDTH / 3;
    private int FIELD_HEIGHT = HEIGHT / 3;
    private int NOBODY = 0;
    private int PLAYER_ONE = 1;
    private int PLAYER_TWO = 2;

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getFIELD_WIDTH() {
        return FIELD_WIDTH;
    }

    public void setFIELD_WIDTH(int FIELD_WIDTH) {
        this.FIELD_WIDTH = FIELD_WIDTH;
    }

    public int getFIELD_HEIGHT() {
        return FIELD_HEIGHT;
    }

    public void setFIELD_HEIGHT(int FIELD_HEIGHT) {
        this.FIELD_HEIGHT = FIELD_HEIGHT;
    }
    
    public int getNOBODY() {
        return NOBODY;
    }

    public void setNOBODY(int NOBODY) {
        this.NOBODY = NOBODY;
    }

    public int getPLAYER_ONE() {
        return PLAYER_ONE;
    }

    public void setPLAYER_ONE(int PLAYER_ONE) {
        this.PLAYER_ONE = PLAYER_ONE;
    }

    public int getPLAYER_TWO() {
        return PLAYER_TWO;
    }

    public void setPLAYER_TWO(int PLAYER_TWO) {
        this.PLAYER_TWO = PLAYER_TWO;
    }
}
