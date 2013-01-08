
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	private String graphic = "craft.png";
    private double dx;
    private double dy;
    private double x;
    private double y;
    private int width;
    private int height;
    private boolean midair;
    private boolean visible;
    private Image image;
    private static Player instance;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(graphic));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 10;
        y = 0;
        this.midair = false;
        Player.instance = this;
	}

    public static Player getInstance() {
        return Player.instance;
    }

	public void move() {
        x += dx;
        y += dy;

        recalculateDY();

        if (x < 1) {
            x = 1;
        }
        if (y - 2*height > Collision.height) {
            this.setMidair(false);
            y = Collision.height - 2* height;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            dy = -5;
            this.setMidair(true);
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
    	if(this.midair)
    		dy += 0.81/10;
    }

    public void setMidair(boolean midair) {
        if(!midair) this.dy = 0;
        this.midair = midair;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return (int) this.height;
    }

    public int getWidth() {
        return (int) this.width;
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
        return new Rectangle((int)x, (int)y, width, height);
    }
}