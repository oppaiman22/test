package tictactoe.main;

import javax.swing.JOptionPane;
import tictactoe.controller.ClientController;
import tictactoe.controller.ServerController;

public class Main {

     public static void main(String[] args) {
        int choice = Integer.parseInt(JOptionPane.showInputDialog("1 for server | 2 for client"));
        if(choice == 1) {
            new ServerController();
        } else if(choice == 2) {
            new ClientController();
        }
    }
    
}
