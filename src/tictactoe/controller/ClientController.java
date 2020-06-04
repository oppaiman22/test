package tictactoe.controller;

import java.io.IOException;
import java.net.Socket;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.UpdatePacket;

public class ClientController extends GameController {
    public ClientController() {
        super(2);
        try {
            socket = new Socket("localhost", getPort());
            connection = new Connection(this, socket);
        } catch (IOException ex) {
            System.out.println("tictactoe.controller.ClientController.<init>()");
        }
    }
    
    @Override
    public void inputReceived(int x, int y) {
        if(isMyTurn()){
           connection.sendPacket(new ClientPlayPacket(x, y));
           fields[x][y]= getPLAYER_TWO();
           gamePanel.repaint();
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