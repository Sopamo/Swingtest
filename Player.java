
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Item {
	private String graphic = "player.png";
    private String graphicRunning = "player_running.png";
    private String graphicJumping = "player_jumping.png";
    private int imageStatus = 1;
    private double ticker = 1;
    private double dx;
    private double dy;
    private boolean midair;
    private boolean visible;
    private Image image;
    private Image imageRunning;
    private Image imageJumping;
    private static Player instance;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(graphic));
        image = ii.getImage();
        ImageIcon i2 = new ImageIcon(this.getClass().getResource(graphicRunning));
        imageRunning = i2.getImage();
        ImageIcon i3 = new ImageIcon(this.getClass().getResource(graphicJumping));
        imageJumping = i3.getImage();
        setWidth(image.getWidth(null));
        setHeight(image.getHeight(null));
        setX(30);
        setY(0);
        Player.instance = this;
	}

    public static Player getInstance() {
        return Player.instance;
    }

	public void move() {
        setY(dy + (int)getY());
        setX(dx + (int)getX());

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
    }

    public int getImageStatus() {
        return imageStatus;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && !getMidair()) {
            dy = -4;
	        setMidair(true);
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void recalculateDY() {
    	dy += 0.81/10;
    }

    public void setMidair(boolean midair) {
        this.midair = midair;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

    public double getDY() {
        return (double) this.dy;
    }

    public double getDX() {
        return (double) this.dx;
    }

    public boolean getMidair() {
	   return midair;
    }

    public Image getImage() {
        if(getMidair()) return imageJumping;
        return (imageStatus == 1) ? image : imageRunning;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight());
    }
}
