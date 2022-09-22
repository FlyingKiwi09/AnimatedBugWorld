package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Web extends Circle {

	private final int MAX_SIZE = 40;
	private final int MIN_SIZE = 0;
	
	public Web(double x, double y, double rad) {
		super(x, y, rad);
		Image image = new Image(("/web.png"));
		this.setFill(new ImagePattern(image));
	}
	
	

}
