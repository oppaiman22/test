package tictactoe.view;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tictactoe.controller.GameController;
import tictactoe.model.GameProperty;

public class GamePanel extends JPanel {

    private final GameController gameController;
    private static Image[] letters;
    
    private int getFIELD_WIDTH(){
        int value = new GameProperty().getFIELD_WIDTH();
        return value;
    }
    
    private int getFIELD_HEIGHT(){
        int value = new GameProperty().getFIELD_HEIGHT();
        return value;
    }
    
    private int getBoardtHeight(){
        int value = new GameProperty().getHEIGHT();
        return value;
    }
    
    private int getBoardwidth(){
        int value = new GameProperty().getWIDTH();
        return value;
    }
    
    private int getNOBODY(){
        int value = new GameProperty().getNOBODY();
        return value;
    }
    
    public GamePanel(GameController gameController) {
        this.gameController = gameController;
        addMouseListener(new Input());
        letters = new Image[2];
        letters[0] = loadImage("/blueX.png");
        letters[1] = loadImage("/resources/blueCircle.png");
    }
    
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(this.getClass().getResource(path));
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
            
    @Override
    public void paint(Graphics newGraphic) {
        super.paint(newGraphic); 
        Graphics2D graphics2D = (Graphics2D) newGraphic;
        BasicStroke basicStroke = new BasicStroke(10);
        graphics2D.setStroke(basicStroke);
        
        for(int x = getFIELD_WIDTH(); 
                x <= getFIELD_WIDTH() * 2; 
                x += getFIELD_WIDTH()) 
        {
            graphics2D.drawLine(x, 0, x, getBoardtHeight());
            graphics2D.drawLine(0, x, getBoardwidth(), x);
        }
        
        
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                int field = gameController.getFields()[x][y];
                
                if(field != getNOBODY()){
                    graphics2D.drawImage(letters[field - 1], 
                                         x * getFIELD_WIDTH(),y * getFIELD_HEIGHT(), 
                                         getFIELD_WIDTH(),getFIELD_HEIGHT(),
                                         null);
                }
            }
        }
    }
    
    class Input extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                gameController.inputReceived(e.getX() / getFIELD_WIDTH(),
                                             e.getY()/ getFIELD_WIDTH());
            }
        }   
    }    
}
