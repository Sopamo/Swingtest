import java.util.ArrayList;
import java.awt.Graphics;

public class Building extends Item {

    private ArrayList obstacles;
	private ArrayList birds;

	public Building(int width, int height, int x, int y) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		this.obstacles = new ArrayList();
		birds = new ArrayList();
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
		moveObstacles();
		moveBirds();
	}

	public void paintComplete(Graphics g) {
		this.paint(g);
		this.paintObstacles(g);
		this.paintBirds(g);
	}

	public ArrayList getObstacles() {
		return obstacles;
	}

	public void spawnObstacles() {
		int lastX = 0;
		for(int i = 0; i <= Board.getRandom(0,2); ++i)
		{
			lastX += Board.getRandom(180,300);
			if(lastX + 120 > getWidth()) break;
			int currentX = lastX + (int) getX();
			System.out.println(currentX);
			Obstacle o = new Obstacle(20,20,currentX,(int) getY()-20);
			this.obstacles.add(o);
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
			o.paint(g);
		}
	}
	
	public void spawnBirds() {
		int lastX	 = (int)getX();
		if(Board.getRandom(0,2) == 1) return;
		
		
		for(int i = 0;; ++i) {
				
			lastX += Board.getRandom(10,30);
			
			if(lastX > getWidth() + getX() - 10) {
				System.out.println("Bird NOT");
				break;
			}
			System.out.println("BIRD");
			
			Bird b = new Bird(lastX, (int)getY() -10);
			
			birds.add(b);	
		
		
		}
	}
	
	public void moveBirds() {
		for(int i=0; i< birds.size();++i) {
			Bird b = (Bird) birds.get(i);
			b.move();
		}	
	
	}	
	
	public void paintBirds(Graphics g) {
		
		for(int i=0; i< birds.size();++i) {
			Bird b = (Bird) birds.get(i);
			b.paint(g);
		}	
	
	
	}
}
