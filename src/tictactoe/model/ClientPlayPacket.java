package tictactoe.model;

import java.io.Serializable;

public class ClientPlayPacket implements Serializable {
    private int coordinateX;
    private int coordinateY;

    public ClientPlayPacket(int inputCoordinateX, int inputCoordinateY) {
        this.coordinateX = inputCoordinateX;
        this.coordinateY = inputCoordinateY;
    }

    public int getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }
    
}
