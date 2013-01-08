import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Building {
	private int x;
	private int y;
	private int width;
	private int height;

	public Building(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}
 
	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public void paint(Graphics g) {
		g.drawRect(x,y,width,height);
	}
}