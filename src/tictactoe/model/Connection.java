/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.controller.GameController;

/**
 *
 * @author fajar
 */
public class Connection implements Runnable {
    
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean running;
    private GameController gameController;

    public Connection(GameController gameController, Socket socket) {
        this.gameController = gameController;
        openConnection(socket);
        new Thread(this).start();
    }
    
    public void openConnection(Socket socket) {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("outputstream created");
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("inputstream created");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendPacket(Object object) {
        try {
            outputStream.reset();
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() {
        try {
            running = false;
            inputStream.close();
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                Object object = inputStream.readObject();
                gameController.packetReceived(object);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
