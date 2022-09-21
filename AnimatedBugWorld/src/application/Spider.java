package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Spider extends Bug {
	
	public Spider(double x, double y, double rad) {
		super(x, y, rad);

		this.eats = "Fly";
		this.smellRange = 5;
		
		
		Image spiderImage = new Image(getClass().getResourceAsStream("/spider.png"), 50,50,true,true);
		this.setFill(new ImagePattern(spiderImage));

	}
}
