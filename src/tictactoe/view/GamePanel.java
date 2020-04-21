/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.view;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import tictactoe.controller.GameController;
import tictactoe.resources.Resource;

/**
 *
 * @author fajar
 */
public class GamePanel extends JPanel {

    private GameController gameController;
    
    public GamePanel(GameController gameController) {
        this.gameController = gameController;
        addMouseListener(new Input());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D graphics2D = (Graphics2D) g;
        BasicStroke basicStroke = new BasicStroke(10);
        graphics2D.setStroke(basicStroke);
        for(int x = gameController.FIELD_WIDTH; x <= gameController.FIELD_WIDTH * 2; x += gameController.FIELD_WIDTH) {
            graphics2D.drawLine(x, 0, x, gameController.HEIGHT);
        }
        for(int y = gameController.FIELD_HEIGHT; y <= gameController.FIELD_HEIGHT * 2; y += gameController.FIELD_HEIGHT) {
            graphics2D.drawLine(0, y, gameController.WIDTH, y);
        }
        Resource resource = new Resource();
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                int field = gameController.getFields()[x][y];
                if(field != gameController.NOBODY)
                    graphics2D.drawImage(resource.letters[field - 1], x * gameController.FIELD_WIDTH, y * gameController.FIELD_HEIGHT, gameController.FIELD_WIDTH, gameController.FIELD_HEIGHT, null);
            }
        }
    }
    
    class Input extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                gameController.inputReceived(e.getX() / gameController.FIELD_WIDTH, e.getY() / gameController.FIELD_HEIGHT);
            }
        }
        
    }
    
}
