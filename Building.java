public class Building extends Item {

	public Building(int width, int height, int x, int y) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
	}
}