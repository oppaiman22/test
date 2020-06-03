package tictactoe.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import tictactoe.controller.GameController;

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
    
    class Listener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            gameController.close();
        }
        
    }
}
