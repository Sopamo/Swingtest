import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Building extends Item {

    private ArrayList obstacles;
	private ArrayList birds;
	private String graphic = "building.png";
	private Image image;
	

	public Building(int width, int height, int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(graphic));
        image = ii.getImage();
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		obstacles = new ArrayList();
		birds = new ArrayList();
		setOrientation(1);
		
	}

	public Building() {
		this(0, 500, Board.getInstance().getWidth()+Board.getRandom(50,250), Board.getRandom(290,420));
		int buildingWidth = Board.getRandom(300,1000);
        buildingWidth += 50 - (buildingWidth % 50);
        this.setWidth(buildingWidth);
        
        if(Board.getRandom(0,2) == 1) {
        	this.setX(getX());
        	this.setY(500-getY()-getHeight());
        	this.setOrientation(-1);
        }
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
		moveObstacles();
		moveBirds();
		flyBirds();
	}

	public void paintComplete(Graphics g) {
		this.paint(g);
		this.paintObstacles(g);
		this.paintBirds(g);
	}

	//public void paint(Graphics g) {
	//	int graphicX = (int)getX();
	//	for(int i = 0; i < getWidth() / 50; ++i)
	//	{
	//		g.drawImage(image, graphicX, (int)getY(), Board.getInstance());
	//		graphicX += 50;
	//	}
	//}

	public ArrayList getObstacles() {
		return obstacles;
	}

	public ArrayList getBirds() {
		return birds;
	}

	public Rectangle getHitbox() {
		return new Rectangle((int)getX(), (int)getY() - 200, getWidth(), getHeight()+ 200);
	}

	public void spawnObstacles() {

		if(Board.getRandom(0,2) != 1) return;
		int lastX = 0;
		for(int i = 0; i <= Board.getRandom(0,2); ++i)
		{
			lastX += Board.getRandom(180,300);
			if(lastX + 120 > getWidth()) break;
			int currentX = lastX + (int) getX();
			if(getOrientation() == -1) {
				Obstacle o = new Obstacle(20,20,currentX,(int) getY()+getHeight());
				this.obstacles.add(o);
			} else {
				Obstacle o = new Obstacle(20,20,currentX,(int) getY()-20);
				this.obstacles.add(o);
			}
			
		}
	}

	public void moveObstacles() {
		if(this.obstacles.size() == 0) return;
		for(int i = 0; i < this.obstacles.size(); ++i) {
			Obstacle o = (Obstacle) this.obstacles.get(i);
			o.move();
		}
	}

	public void paintObstacles(Graphics g) {
		
		if(this.obstacles.size() == 0) return;
		for(int i = 0; i < this.obstacles.size(); ++i) {
			Obstacle o = (Obstacle) this.obstacles.get(i);
			if(o.isVisible())
				o.paint(g);
		}
	}
	
	public void spawnBirds() {
		int lastX	 = (int)getX();
		if(Board.getRandom(0,2) == 1) return;
		
		
		for(int i = 0;; ++i) {
				
			lastX += Board.getRandom(10,30);
			
			if(lastX > getWidth() + getX() - 10) {
				break;
			}
			
			if(getOrientation() == -1) {
				Bird b = new Bird(lastX, (int)getY() +getHeight());
				birds.add(b);
			} else {
				Bird b = new Bird(lastX, (int)getY() -10);
				birds.add(b);
			}
			
				
		
		
		}
	}
	
	public void moveBirds() {
		for(int i=0; i< birds.size();++i) {
			Bird b = (Bird) birds.get(i);
			b.move();
		}	
	
	}

	public void flyBirds() {
		if(birds.size() == 0) return;

		for(int i=0;i<birds.size();++i) {
			Bird b = (Bird) birds.get(i);
			if(b.getProperties() == Bird.FLYING) b.fly();
		}
	}	
	
	public void paintBirds(Graphics g) {
		
		for(int i=0; i< birds.size();++i) {
			Bird b = (Bird) birds.get(i);
			b.paint(g);
		}	
	
	
	}
}
