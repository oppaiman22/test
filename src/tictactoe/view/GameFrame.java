/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import tictactoe.controller.GameController;

/**
 *
 * @author fajar
 */
public class GameFrame extends JFrame {
    
    private int width;
    private int height;
    private GameController gameController;
    
    public GameFrame(GameController gameController, int width, int height) {
        this.gameController = gameController;
        this.width = width;
        this.height = height;
        initComponent();
    }
    
    public void initComponent() {
        setTitle("Tic Tac Toe");
        setResizable(false);
        Dimension preferredSize = new Dimension(width, height);
        getContentPane().setPreferredSize(preferredSize);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new Listener());
    }
//    
//    public static void main(String[] args) {
//        new GameFrame().setVisible(true);
//        System.out.println("game frame loaded");
//    }
    
    class Listener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            gameController.close();
        }
        
    }
}
