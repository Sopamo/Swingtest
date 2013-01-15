class Obstacle extends Item {

	private boolean active;

	public Obstacle(int width, int height, int x, int y) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
	}

}