import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bird extends Item {

	public final static 		BirdProperties SITTING = BirdProperties.SITTING,
											   FLYING  = BirdProperties.FLYING;

	private String graphic = "bird.png";
	private Image image;
	private BirdProperties properties;
	
	public Bird(int x, int y) {
		ImageIcon i = new ImageIcon(this.getClass().getResource(graphic));
		image = i.getImage();
		
		setWidth(image.getWidth(null));
		setHeight(image.getHeight(null));
		
		setProperties(SITTING);
		
		setX(x);
		setY(y);
	}
	
	public BirdProperties getProperties() {
		return properties;
	}
	
	public void setProperties(BirdProperties b) {
		properties = b;
	}

	public Image getImage() {
		return image;
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
	}

	public void paint(Graphics g) {
		g.drawImage(getImage(), (int)getX(), (int)getY(), Board.getInstance());
	}

	public void fly() {
		double lastX = getX();
		double lastY = getY();
		lastX += Board.getRandom(-1,5);
		lastY -= Board.getRandom(0,10)/5;		
		setX(lastX);
		setY(lastY);
		
		
	}


}
