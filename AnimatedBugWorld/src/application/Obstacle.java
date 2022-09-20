package application;

public class Obstacle extends WorldObject {
	
	private char symbol;
	
	public Obstacle() {
		super();
		this.symbol = '^';
		this.type = "Obstacle";
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
