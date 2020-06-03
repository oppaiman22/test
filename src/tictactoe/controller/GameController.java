package tictactoe.controller;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.GameProperty;
import tictactoe.model.UpdatePacket;
import tictactoe.view.GameFrame;
import tictactoe.view.GamePanel;

public abstract class GameController {
    protected GameProperty gameProperty;
    protected int[][] fields;
    protected int currentPlayer;
    protected int thisPlayer;
    protected GamePanel gamePanel;
    protected GameFrame gameFrame;
    protected Socket socket;
    protected Connection connection;
    
    public abstract void packetReceived(Object object);

    public GameController(int thisPlayer) {
        gameProperty = new GameProperty();
        this.thisPlayer = thisPlayer;
        gameFrame = new GameFrame(this, gameProperty.getFIELD_WIDTH(), gameProperty.getFIELD_HEIGHT());
        gamePanel = new GamePanel(this);
        fields = new int[3][3];
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        currentPlayer = gameProperty.getPLAYER_ONE();
    }
    
    public int[][] getFields() {
        return fields;
    }
    
    protected boolean isMyTurn() {
        if(thisPlayer == currentPlayer) return true;
        return false;
    }
    
    protected void showWinner(int winner) {
        if(winner == gameProperty.getNOBODY()) 
            JOptionPane.showMessageDialog(null, "TIE!");
        else 
            JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game!");
    }
    
    public void inputReceived(int x, int y) {
        x = x /  gameProperty.getFIELD_HEIGHT();
        y = y /gameProperty.getFIELD_WIDTH();
          if(isMyTurn())
            updateField(x, y);
    }
    
    public void close(){
        connection.close();
        try {
            socket.close();
        } catch (IOException ex) {
        }
    }    
    
    protected void updateField(int x, int y) {
        if(fields[x][y] == gameProperty.getNOBODY()) {
            fields[x][y] = currentPlayer;
            
            if(currentPlayer == gameProperty.getPLAYER_ONE()) {
                currentPlayer = gameProperty.getPLAYER_TWO();
                connection.sendPacket(new UpdatePacket(fields, currentPlayer));
            } else if(currentPlayer == gameProperty.getPLAYER_TWO()) {
                currentPlayer = gameProperty.getPLAYER_ONE();
                connection.sendPacket(new ClientPlayPacket(x, y));
            }
             
            gamePanel.repaint();
            int winner = checkWin();
            
            if(winner != gameProperty.getNOBODY()) {
                endGame(winner);
            } else {
                if(countEmptyField() == 9)
                    endGame(winner);
            }
        }
    }
    
    private int countEmptyField(){
        int emptyCount = 0;
        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                if(fields[a][b] == gameProperty.getNOBODY()) {
                    emptyCount++;
                }
            }
        }
        return emptyCount;
    }
    
    private void endGame(int winner) {
        showWinner(winner);
        connection.sendPacket(new GameEndPacket(winner));
    }
        
     public int checkWin() {
        for(int player = 1; player <= 2; player++) {

            if(checkHorizontalWin(player))
                return player;

            if(checkVerticalWin(player))
                return player;

            if(checkDiagonalWin(player))
                return player;
            
        }
        return 0;
    }

    private boolean checkHorizontalWin(int player){
        for(int y = 0; y < 3; y++) {
            int playerCount = 0;
            for(int x = 0; x < 3; x++) {
                if(fields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    private boolean checkVerticalWin(int player){
        for(int x = 0; x < 3; x++) {
            int playerCount = 0;
            for(int y = 0; y < 3; y++) {
                if(fields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    public boolean checkDiagonalWin(int player){
        int playerCount = 0;
        for(int coordinate = 0; coordinate < 3; coordinate++) 
            if(fields[coordinate][coordinate] == player) playerCount++;

        if(playerCount == 3)
            return true;
        
        playerCount = 0;
        for(int coordinate = 0; coordinate < 3; coordinate++) 
            if(fields[2-coordinate][coordinate] == player) playerCount++;

        if(playerCount == 3)
            return true;

        return false;
    }
}
