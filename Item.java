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
