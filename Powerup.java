class Powerup extends Item
{
	private boolean active;
	private int type;

	public static final int TYPE_JETPACK = 0;
	public static final int TYPE_GRAVITY = 1;
	public static final int TYPE_FASTER = 2;
	public static final int TYPE_SLOWER = 3;

	public Powerup(int width, int height, int x, int y) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		this.type = 1;
		this.active = true;
	}

	public void deactivate() {
		this.active = false;
	}

	public boolean isActive() {
		return this.active;
	}

	public void collide() {
		switch(type)
		{
			case 0: 
				break;
			case 1: 
				Board.getInstance().toggleGravity();
				break;
			case 2: 
				break;
			case 3: 
				break;
		}
	}

	public void move() {
		setX(getX() - Board.getInstance().getSpeed());
	}
}