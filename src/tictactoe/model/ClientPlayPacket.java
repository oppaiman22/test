/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.model;

import java.io.Serializable;

/**
 *
 * @author fajar
 */
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
