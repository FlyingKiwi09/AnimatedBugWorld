package application;

public class WorldObject {
	protected int xPos;
	protected int yPos;
	protected String type;
	
	public WorldObject() {
		this.xPos = (int)Math.floor(1+ Math.random()*(World.WORLD_WIDTH-2));
		this.yPos = (int)Math.floor(1+ Math.random()*(World.WORLD_HEIGTH-2));
		this.type = "";
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void eaten() {
		
	}
	
	public void eats() {
		
	}
	
}
