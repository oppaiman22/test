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
//import tictactoe.model.Server;
import tictactoe.model.UpdatePacket;

/**
 *
 * @author fajar
 */
public class ServerController extends GameController{      
    public ServerController() {
        super(1);//assign wich player is playing
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
