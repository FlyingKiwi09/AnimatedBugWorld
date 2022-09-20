package application;

public class Plant extends WorldObject {

	private int size;
	private final int MAX_SIZE = 9;
	private final int MIN_SIZE = 0;
	
	public Plant() {
		super();
		this.size = (int)Math.floor(0+ Math.random()*3);
		this.type = "Plant";
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void grow() {
		if (this.size < MAX_SIZE) {
			this.size++;
		}
	}
	
	public void eaten() {
		if (this.size > 0) {
			this.size--;
		}
	}
	
	
}
