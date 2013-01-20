
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.IOException;

public class Player extends Item {
	private String graphic = "player.png";
    private String graphicRunning = "player_running.png";
    private String graphicJumping = "player_jumping.png";
    private int imageStatus = 1;
    private double ticker = 1;
    private boolean midair;
    private boolean visible;
    private BufferedImage image;
    private BufferedImage imageRunning;
    private BufferedImage imageJumping;
    private BufferedImage imageR;
    private BufferedImage imageRunningR;
    private BufferedImage imageJumpingR;
    private static Player instance;

    public Player() {
        try
        {
            image = ImageIO.read(this.getClass().getResource(graphic));
            imageRunning = ImageIO.read(this.getClass().getResource(graphicRunning));
            imageJumping = ImageIO.read(this.getClass().getResource(graphicJumping));
            imageR = rotate180(image);
            imageRunningR = rotate180(imageRunning);
            imageJumpingR = rotate180(imageJumping);
        } catch(java.io.IOException e)
        {

        }
        setWidth(image.getWidth(null));
        setHeight(image.getHeight(null));
        setX(30);
        setY(300);
        Player.instance = this;
    }

    public static Player getInstance() {
        return Player.instance;
    }

    public void paint(Graphics g)
    {
        g.drawImage(getImage(), (int)getX(), (int)getY(), Board.getInstance());
    }

    public BufferedImage rotate180( BufferedImage inputImage ) {
        int width = inputImage.getWidth(); //the Width of the original image
        int height = inputImage.getHeight();//the Height of the original image

        BufferedImage returnImage = new BufferedImage( width, height, inputImage.getType()  );

        for( int x = 0; x < width; x++ ) {
                for( int y = 0; y < height; y++ ) {   
                    returnImage.setRGB( x, height - y - 1, inputImage.getRGB( x, y  )  );
                }
        }

        return returnImage;
    }



public void move() {
    setY(getDY() + (int)getY());
    setX(getDX() + (int)getX());

    recalculateDY();

        // Animate player by changing the image every x times, dependant on current game speed.

        // Increase ticker by 1/2 of speed
    double tickerIncrease = Board.getInstance().getSpeed() / 2;
        // Increase by at least 1
    if(tickerIncrease < 1) 
        tickerIncrease = 1;
        // Increase ticker
    ticker = (ticker + tickerIncrease);
        // Set ticker to 0 every 30 steps
    if(ticker > 30)
        ticker = 0;
        // When ticker is 0 change image
    if(ticker == 0) {
        imageStatus = (imageStatus + 1)%2;
    }

        // Don't let the player run out of the left or the right
    if (getX() < 1) {
        setX(1);
    }
    if(getX() > Collision.width) {
        setX(Collision.width);
    }

        // Player fell down - stop game
    if (getY() > Collision.height) {
        Board.getInstance().setEndgameMessage("Greet the concrete.");
        Board.getInstance().stopGame();
    }

    if (getY() < 0) {
        Board.getInstance().setEndgameMessage("Greet the sky.");
        Board.getInstance().stopGame();
    }
}

public int getImageStatus() {
    return imageStatus;
}

public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_SPACE && !getMidair()) {
        setDY(-4 * Board.getInstance().getGravity());
        setMidair(true);
    }

    if (key == KeyEvent.VK_LEFT) {
        setDX(-2);
    }

    if (key == KeyEvent.VK_RIGHT) {
        setDX(2);
    }
    if (key == KeyEvent.VK_E) {
     Board.getInstance().toggleGravity();
     setDY(-0.5);
 }
}

public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
        setDX(0);
    }

    if (key == KeyEvent.VK_RIGHT) {
        setDX(0);
    }
}

public void setMidair(boolean midair) {
    this.midair = midair;
}

public boolean getMidair() {
    return midair;
}

public BufferedImage getImage() {
    BufferedImage i;
    if(Board.getInstance().getGravity() == 1)
    {
        i = (imageStatus == 1) ? image : imageRunning;

        if(getMidair()) i = imageJumping;
    } else {
        i = (imageStatus == 1) ? imageR : imageRunningR;

        if(getMidair()) i = imageJumpingR;
    }
    return i;
    
}


public Rectangle getBounds() {
    return new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight());
}
}
