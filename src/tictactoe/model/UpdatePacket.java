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
public class UpdatePacket implements Serializable {
    
    private int[][] fields;
    private int currentPlayer;

    public UpdatePacket(int[][] fields, int currentPlayer) {
        this.fields = fields;
        this.currentPlayer = currentPlayer;
    }

    public int[][] getFields() {
        return fields;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
}
