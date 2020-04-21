/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.model.Client;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.UpdatePacket;

/**
 *
 * @author fajar
 */
public class ClientController extends GameController {
    
    private Socket socket;
    private Connection connection;

    public ClientController() {
        super(2);
        try {
            socket = new Socket("localhost", PORT);
            connection = new Connection(this, socket);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inputReceived(int x, int y) {
        if(isMyTurn())
            connection.sendPacket(new ClientPlayPacket(x, y));
    }

    @Override
    public void close() {
        connection.close();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof UpdatePacket) {
            UpdatePacket packet = (UpdatePacket) object;
            fields = packet.getFields();
            currentPlayer = packet.getCurrentPlayer();
        } else if(object instanceof GameEndPacket) {
            GameEndPacket packet = (GameEndPacket) object;
            showWinner(packet.getWinner());
        }
        gamePanel.repaint();
    }
    
}
