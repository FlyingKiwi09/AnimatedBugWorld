package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class Fly extends Bug {
	
	public Fly(double x, double y) {
		super(x, y);
		this.species = "Fly";
		this.symbol = 'F';
		this.eats = "Plant";
//		this.type = "Fly";
		this.smellRange = 20;
		Image flyImage = new Image(getClass().getResourceAsStream("/fly.png"), 20,20,true,true);
		this.imageView.setImage(flyImage);
		this.setGraphic(this.imageView);
	}
}
