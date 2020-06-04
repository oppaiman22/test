package tictactoe.controller;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import tictactoe.model.Connection;
import tictactoe.model.GameProperty;
import tictactoe.view.GameFrame;
import tictactoe.view.GamePanel;

public abstract class GameController {
    protected int[][] fields;
    protected int currentPlayer;
    protected int thisPlayer;
    protected GamePanel gamePanel;
    protected Socket socket;
    protected Connection connection;
    
    public abstract void packetReceived(Object object);
    public abstract void inputReceived(int x, int y);

    public GameController(int thisPlayer) { 
        this.thisPlayer = thisPlayer;
        fields = new int[3][3];
        gamePanel = new GamePanel(this);
        currentPlayer = getPLAYER_ONE();
        GameFrame gameFrame = new GameFrame(this, getWIDTH(), getHEIGHT());
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);  
    }
    
    protected int getPLAYER_ONE(){
        int value = new GameProperty().getPLAYER_ONE();
        return value;
    }
    
    protected int getPLAYER_TWO(){
        int value = new GameProperty().getPLAYER_TWO();
        return value;
    }
    
    private int getWIDTH(){
        int value = new GameProperty().getHEIGHT();
        return value;
    }
    
    private int getHEIGHT(){
        int value = new GameProperty().getWIDTH();
        return value;
    }
    
    protected int getNOBODY(){
        int value = new GameProperty().getNOBODY();
        return value;
    }
    
    protected int getPort(){
        int value = new GameProperty().getPORT();
        return value;
    }
    
    public void close(){
        connection.close();
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("tictactoe.controller.GameController.close()");
        }
    }   
    
    public int[][] getFields() {
        return fields;
    }

    protected boolean isMyTurn() {
        return thisPlayer == currentPlayer;
    }
    
    protected void showWinner(int winner) {
        if(winner == getNOBODY()) {
            JOptionPane.showMessageDialog(null, "TIE!");
        }else {
            JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game!");
        }
    }  
        
}