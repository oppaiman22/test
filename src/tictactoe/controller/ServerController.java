/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.Server;
import tictactoe.model.UpdatePacket;

/**
 *
 * @author fajar
 */
public class ServerController extends GameController {

    private ServerSocket serverSocket;
    private Socket socket;
    private Connection connection;
    
    public ServerController() {
        super(1);
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void inputReceived(int x, int y) {
        if(isMyTurn())
            updateField(x, y);
    }

    @Override
    public void close() {
        connection.close();
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof ClientPlayPacket) {
            ClientPlayPacket packet = (ClientPlayPacket) object;
            updateField(packet.getX(), packet.getY());
        }
    }
    
    private void updateField(int x, int y) {
        if(fields[x][y] == NOBODY) {
            fields[x][y] = currentPlayer;
            if(currentPlayer == PLAYER_ONE) {
                currentPlayer = PLAYER_TWO;
            } else if(currentPlayer == PLAYER_TWO) {
                currentPlayer = PLAYER_ONE;
            }
            connection.sendPacket(new UpdatePacket(fields, currentPlayer));
            gamePanel.repaint();
            int winner = checkWin();
            if(winner != NOBODY) {
                endGame(winner);
            } else {
                int emptyCount = 0;
                for(int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(fields[a][b] == NOBODY) {
                            emptyCount++;
                        }
                    }
                }
                if(emptyCount == 9)
                    endGame(winner);
            }
        }
    }
    
    private void endGame(int winner) {
        showWinner(winner);
        connection.sendPacket(new GameEndPacket(winner));
    }
    
    public void sendPacket(Object object, ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private int checkWin() {
        for(int player = PLAYER_ONE; player <= PLAYER_TWO; player++) {
            for(int y = 0; y < 3; y++) {
                int playerCount = 0;
                for(int x = 0; x < 3; x++) {
                    if(fields[x][y] == player) {
                        playerCount++;
                    }
                }
                if(playerCount == 3)
                    return player;
            }
            for(int x = 0; x < 3; x++) {
                int playerCount = 0;
                for(int y = 0; y < 3; y++) {
                    if(fields[x][y] == player) {
                        playerCount++;
                    }
                }
                if(playerCount == 3) {
                    return player;
                }
            }
            int playerCount = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(fields[coordinate][coordinate] == player)
                    playerCount++;
            }
            if(playerCount == 3)
                return player;
            
            playerCount = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(fields[2-coordinate][coordinate] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return player;
        }
        return NOBODY;
    }
}
