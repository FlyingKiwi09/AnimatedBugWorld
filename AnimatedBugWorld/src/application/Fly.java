package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.ImagePattern;

public class Fly extends Bug {
	
	private boolean caught = false;

	public Fly(double x, double y, double rad) {
		super(x, y, rad);
		this.eats = "Plant";
		this.smellRange = 20;
		Image flyImage = new Image(getClass().getResourceAsStream("/fly.png"), 20,20,true,true);		
		this.setFill(new ImagePattern(flyImage));
	}
	
	public boolean isCaught() {
		return caught;
	}

	public void setCaught(boolean caught) {
		this.caught = caught;
	}
	
	@Override
	public void update(World world) {
		
		if (!caught) {
			Web nextTo = nextToWeb(world);
			if (nextTo!=null) {
				caught = true;
			}
		}
		
		if(!caught) {
			animate(world.getWidth(), world.getHeight());
		}
		
	}
	
	
	public Web nextToWeb(World world) {
		Web nextTo = null;
		ArrayList<Web> webs = world.getSpider().getWebs();
		for (Web web: webs) {
			double webX = web.getCenterX();
			double webY = web.getCenterY();
			double webRad = web.getRadius();
			
			if ((this.getTranslateX() > (webX - webRad)) && (this.getTranslateX() < (webX + webRad))) {
				
				if ((this.getTranslateY() > (webY - webRad)) && (this.getTranslateY() < (webY + webRad))) {
					nextTo = web;
				}
			}
			
		}
		return nextTo;
	}
	
}
