package tictactoe.controller;

import java.io.IOException;
import java.net.ServerSocket;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.UpdatePacket;
import tictactoe.model.WinnerChekker;

public class ServerController extends GameController{      
    public ServerController() {
        super(1);
        try {
            ServerSocket serverSocket = new ServerSocket(getPort());
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException ex) {
            System.out.println("tictactoe.controller.ServerController.<init>()");
        }
        
    }
    
    private void updateField(int x, int y) {
        if(fields[x][y] == getNOBODY()) {
            
            fields[x][y] = currentPlayer;
       
            if(currentPlayer == getPLAYER_ONE()) {
                currentPlayer = getPLAYER_TWO();
                connection.sendPacket(new UpdatePacket(fields, currentPlayer));
            } else  {
                currentPlayer = getPLAYER_ONE();
                connection.sendPacket(new ClientPlayPacket(x, y));
            }
             
            gamePanel.repaint();
            int winner = checkWin();
            int countField = countEmptyField();
            
            if(winner != getNOBODY() || countField == 9) {
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
                if(fields[a][b] == getNOBODY()) {
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
