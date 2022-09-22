package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Ladybug extends Bug {

	public Ladybug(double x, double y, double rad) {
		super(x, y, rad);
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
	
	@Override
	public void update(World world) {
		this.lastEaten++;

		if(stoppedCount == 0) {
			animate(world.getWidth(), world.getHeight());
			if (this.lastEaten > 20) {
				Plant toEat = nextToPlant(world);
				
				if (toEat != null && toEat.getRadius() > 20) {
					
					eat(toEat);
				}
			}
		} else {
			stoppedCount--;
		}
		
	}

}
