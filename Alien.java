

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Alien {

    private String graphic = "alien.png";

    private double x;
    private double y;
    private double dy;
    private int width;
    private int height;
    private boolean visible;
    private boolean flying;
    private Image image;

    public Alien(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(graphic));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.flying = false;
        this.x = x;
        this.y = y;
    }


    public void move() {
        if (Player.getInstance().getX() < x) 
            x -= 0.5;
        else
            x += 0.5;

        if(!this.flying && Player.getInstance().getY() < y) {
            dy -= 4;
        }
        y += dy;

        if (x < 1) {
            x = 1;
        }
        if (y > 200) {
            y = 200;
        }

        recalculateDY();
    }

    public void recalculateDY() {
        if(y < 200) {
            this.flying = true;
            dy += 0.81/10;
        } else {
            dy = 0;
            this.flying = false;
        }
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}