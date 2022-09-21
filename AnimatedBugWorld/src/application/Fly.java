package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.ImagePattern;

public class Fly extends Bug {
	
	public Fly(double x, double y) {
		super(x, y);
		this.eats = "Plant";
//		this.type = "Fly";
		this.smellRange = 20;
		Image flyImage = new Image(getClass().getResourceAsStream("/fly.png"), 20,20,true,true);
//		this.imageView.setImage(flyImage);
//		this.setGraphic(this.imageView);
		
		
		this.setFill(new ImagePattern(flyImage));
	}
}
