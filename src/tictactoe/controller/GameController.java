/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import javax.swing.JOptionPane;
import tictactoe.view.GameFrame;
import tictactoe.view.GamePanel;

/**
 *
 * @author fajar
 */
public abstract class GameController {
    public final int PORT = 5555;
    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    public final int FIELD_WIDTH = WIDTH / 3;
    public final int FIELD_HEIGHT = HEIGHT / 3;
    public final int NOBODY = 0;
    public final int PLAYER_ONE = 1;
    public final int PLAYER_TWO = 2;
    protected int[][] fields;
    protected int currentPlayer;
    protected int thisPlayer;
    protected GamePanel gamePanel;
    protected GameFrame gameFrame;

    public GameController(int thisPlayer) {
        this.thisPlayer = thisPlayer;
        gameFrame = new GameFrame(this, WIDTH, HEIGHT);
        gamePanel = new GamePanel(this);
        fields = new int[3][3];
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        currentPlayer = PLAYER_ONE;
    }
    
    public void tes(){
        System.out.println("!@#$%");
    }
    
    public int[][] getFields() {
        return fields;
    }
    
    protected boolean isMyTurn() {
        if(thisPlayer == currentPlayer) {
            return true;
        }
        return false;
    }
    
    protected void showWinner(int winner) {
        if(winner == NOBODY) {
            JOptionPane.showMessageDialog(null, "TIE!");
        } else 
            JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game!");
    }
    
    public abstract void inputReceived(int x, int y);
    public abstract void close();
    public abstract void packetReceived(Object object);
}
