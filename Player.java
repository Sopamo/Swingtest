
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Item {
	private String graphic = "player.png";
    private String graphicRunning = "player_running.png";
    private int imageStatus = 1;
    private double dx;
    private double dy;
    private boolean midair;
    private boolean visible;
    private Image image;
    private Image imageRunning;
    private static Player instance;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(graphic));
        image = ii.getImage();
        ImageIcon i2 = new ImageIcon(this.getClass().getResource(graphicRunning));
        imageRunning = i2.getImage();
        setWidth(image.getWidth(null));
        setHeight(image.getHeight(null));
        setX(10);
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

        if (getX() < 1) {
            setX(1);
        }
        if(getX() > Collision.width) {
            setX(Collision.width);
        }
        if (getY() > Collision.height) {
            Board.getInstance().stopGame();
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            dy = -5;
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

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight());
    }
}