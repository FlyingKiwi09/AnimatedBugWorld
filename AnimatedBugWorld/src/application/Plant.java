package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Plant extends Circle {

	private final int MAX_SIZE = 40;
	private final int MIN_SIZE = 0;
	
	public Plant(double x, double y, double rad) {
		super(x, y, rad);
		Image image = new Image(("/plant.jfif"));
		this.setFill(new ImagePattern(image));
	}
	
	
	public void grow() {		
		int shouldGrow = (int)(0 + Math.random()*50); // gives 1/50 chance of growing
		if (this.getRadius() < MAX_SIZE && (shouldGrow == 1)) {
			this.setRadius(this.getRadius()+ 1);
		}
	}
	
	public void eaten() {
		if (this.getRadius() > 0) {
			this.setRadius(this.getRadius()- 10);
		}
	}
	
	
}
