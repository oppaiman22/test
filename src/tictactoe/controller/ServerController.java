package tictactoe.controller;

import java.io.IOException;
import java.net.ServerSocket;
import tictactoe.model.ClientPlayPacket;
import tictactoe.model.Connection;

public class ServerController extends GameController{      
    public ServerController() {
        super(1);
        try {
            ServerSocket serverSocket = new ServerSocket(gameProperty.getPORT());
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException ex) {
        }
        
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof ClientPlayPacket) {
            ClientPlayPacket packet = (ClientPlayPacket) object;
            updateField(packet.getX(), packet.getY());
        }
    }    
}
