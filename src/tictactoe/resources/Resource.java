/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author fajar
 */
public class Resource {
    public static Image[] letters;
    
    public Resource() {
        letters = new Image[2];
        System.out.println("lalalala");
        letters[0] = loadImage("/blueX.png");
        System.out.println("x loaded");
        letters[1] = loadImage("/resources/blueCircle.png");
        System.out.println("o loaded");
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
}
