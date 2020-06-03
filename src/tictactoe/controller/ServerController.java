package tictactoe.controller;

import java.io.IOException;
import java.net.ServerSocket;
import tictactoe.model.Connection;
import tictactoe.model.WinnerChekker;

public class ServerController extends GameController{      
    public ServerController() {
        super(1);
        try {
            ServerSocket serverSocket = new ServerSocket(gameProperty.getPORT());
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException ex) {
            System.out.println("tictactoe.controller.ServerController.<init>()");
        }
        
    }
    
    private void updateField(int x, int y) {
        if(fields[x][y] == gameProperty.getNOBODY()) {
            
            fields[x][y] = currentPlayer;
       
            if(currentPlayer == gameProperty.getPLAYER_ONE()) {
                currentPlayer = gameProperty.getPLAYER_TWO();
                connection.sendPacket(new UpdatePacket(fields, currentPlayer));
            } else  {
                currentPlayer = gameProperty.getPLAYER_ONE();
                connection.sendPacket(new ClientPlayPacket(x, y));
            }
             
            gamePanel.repaint();
            int winner = checkWin();
            int countField = countEmptyField();
            
            if(winner != gameProperty.getNOBODY() || countField == 9) {
                endGame(winner);
            }
        }
    }

    
    private void endGame(int winner) {
        showWinner(winner);
        connection.sendPacket(new GameEndPacket(winner));
    }
    
    
    private int countEmptyField(){
        int fieldCount = 0;
        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                if(fields[a][b] == gameProperty.getNOBODY()) {
                    fieldCount++;
                }
            }
        }
        return fieldCount;
    }
    
    
    
    private int checkWin() {
        return new WinnerChekker(fields).checking();
    }
    
    @Override
    public void inputReceived(int x, int y) {
        if(isMyTurn())
            updateField(x, y);
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof ClientPlayPacket) {
            ClientPlayPacket packet = (ClientPlayPacket) object;
            updateField(packet.getX(), packet.getY());
        }
    }    
}
