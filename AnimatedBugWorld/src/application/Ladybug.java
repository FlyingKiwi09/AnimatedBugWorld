package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Ladybug extends Bug {

	public Ladybug(double x, double y) {
		super(x, y);
		this.eats = "Plant";
		this.smellRange = 20;
		Image ladybugImage = new Image(getClass().getResourceAsStream("/ladyBug.png"), 20,20,true,true);
		this.setFill(new ImagePattern(ladybugImage));
	}
	
	@Override
	public void eat(Plant toEat) {
		this.stoppedCount = 20; // bug must stop to eat
		toEat.setRadius(toEat.getRadius()-5);
		this.lastEaten = 0;
	}

}
