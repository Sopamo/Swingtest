import java.lang.Math;
import java.awt.Graphics;

class Obstacle extends Item {

	private boolean active;
	private boolean visible;
	private double flyX;
	private double initalY;
	private boolean flying;
	private int flyingHeight;

	public Obstacle(int width, int height, int x, int y) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		initalY = y;
		flyingHeight = Board.getRandom(10,30);
		flying = false;
		active = true;
		visible = true;
	}

	public void deactivate() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void move() {
		if(flying) 
			fly();
		setX(getX() - Board.getInstance().getSpeed());
	}

	public void paint(Graphics g) {
		g.drawRect((int)getX()+(int)(flyX*10),(int)getY(),getWidth(),getHeight());
	}

	public void attachWings() {
		flying = true;
	}

	public void fly() {
		double newY = -1 * Math.pow(flyX,2) + flyingHeight;
		setY(initalY-newY);
		flyX += 0.15;
		if(flyX > 10) visible = false;
	}

	public boolean isVisible()
	{
		return visible;
	}

}