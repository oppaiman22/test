/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.main;

import javax.swing.JOptionPane;
import tictactoe.controller.ClientController;
import tictactoe.controller.ServerController;

/**
 *
 * @author fajar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice = Integer.parseInt(JOptionPane.showInputDialog("1 for server | 2 for client"));
        if(choice == 1) {
            System.out.println("loading server");
            new ServerController();
        } else if(choice == 2) {
            new ClientController();
        }
    }
    
}
