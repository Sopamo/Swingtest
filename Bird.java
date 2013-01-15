import java.awt.Image;

import javax.swing.ImageIcon;

public class Bird extends Item {

	private String graphic = "bird.png";
	private Image image;
	
	
	public Bird(int x, int y) {
		ImageIcon i = new ImageIcon(this.getClass().getResource(graphic));
		image = i.getImage();
		
		setWidth(image.getWidth(null));
		setHeight(image.getHeight(null));
		
		setX(x);
		setY(y);
	}

	public Image getImage() {
		return image;
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
	}




}