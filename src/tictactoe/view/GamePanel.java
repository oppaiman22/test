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
import tictactoe.model.GameProperty;
import tictactoe.resources.Resource;

/**
 *
 * @author fajar
 */
public class GamePanel extends JPanel {

    private GameController gameController;
    private GameProperty gameProperty;
    
    public GamePanel(GameController gameController) {
        gameProperty = new GameProperty();
        this.gameController = gameController;
        addMouseListener(new Input());
    }
            
    @Override
    public void paint(Graphics newGraphic) {
        super.paint(newGraphic); 
        Graphics2D graphics2D = (Graphics2D) newGraphic;
        BasicStroke basicStroke = new BasicStroke(10);
        graphics2D.setStroke(basicStroke);
        
        for(int x = gameProperty.getFIELD_WIDTH(); 
                x <= gameProperty.getFIELD_WIDTH() * 2; 
                x += gameProperty.getFIELD_WIDTH()) 
        {
            graphics2D.drawLine(x, 0, x, gameProperty.getFIELD_HEIGHT());
            graphics2D.drawLine(0, x, gameProperty.getWIDTH(), x);
        }
        
        Resource resource = new Resource();
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                int field = gameController.getFields()[x][y];
                if(field != gameProperty.getNOBODY())
                    graphics2D.drawImage(resource.letters[field - 1], 
                                         x * gameProperty.getFIELD_WIDTH(), 
                                         y * gameProperty.getFIELD_HEIGHT(), 
                                         gameProperty.getFIELD_WIDTH(), 
                                         gameProperty.getFIELD_HEIGHT(),
                                         null);
            }
        }
    }
    
    class Input extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                gameController.inputReceived(e.getX(),e.getY());
            }
        }   
    }    
}
