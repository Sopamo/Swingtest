import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Item {
	
	private double x;
	private double y;
	private int width;
	private int height;
	private double dx;
    private double dy;
    private static int orienation = -1;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDX(double dx) {
		this.dx = dx;
	}

	public void setDY(double dy) {
        this.dy = dy;
    }

    public double getDY() {
        return (double) this.dy ;
    }

    public double getDX() {
        return (double) this.dx;
    }

    public static int getOrientation() {
		return orienation;
	} 

	public static void setOrientation(int or) {
		orienation = or;
	}

	public void recalculateDY() {
    	setDY(getDY() + 0.7/10 * Board.getInstance().getGravity());
    }

	public boolean intersects(Item item) {
		Rectangle r1 = new Rectangle((int)getX(),(int)getY(),getWidth(),getHeight());
		Rectangle r2 = new Rectangle((int)item.getX(),(int)item.getY(),item.getWidth(),item.getHeight());
		return r1.intersects(r2);
	}
	
	public boolean intersects(Rectangle r) {
		Rectangle s = new Rectangle((int)getX(),(int)getY(),getWidth(),getHeight());
		return s.intersects(r);
	}

	public Item getItem() {
		return this;
	}

	public void paint(Graphics g) {
		g.drawRect((int)getX(),(int)getY(),getWidth(),getHeight());
	}

}
