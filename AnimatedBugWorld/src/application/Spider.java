package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Spider extends Bug {
	
	private ArrayList<Web> webs = new ArrayList<Web>();
	private double readyToWeb = 100;
	
	public Spider(double x, double y, double rad) {
		super(x, y, rad);

		this.eats = "Fly";
		this.smellRange = 5;
		
		
		Image spiderImage = new Image(getClass().getResourceAsStream("/spider.png"), 50,50,true,true);
		this.setFill(new ImagePattern(spiderImage));

	}
	
	@Override
	public void update(World world) {
		animate(world.getWidth(), world.getHeight());
		if (readyToWeb < 100) {
			readyToWeb++;
		}
		
		if (nextToLB(world)) {
			this.dead = true;
		}
		
	}
	
	
	public boolean nextToLB(World world) {
		boolean nextTo = false;
		ArrayList<Bug> bugs = world.getBugs();
		for (Bug bug: bugs) {
			
			if (bug instanceof Ladybug) {
				double webX = bug.getTranslateX();
				double webY = bug.getTranslateY();
				double webRad = bug.getRadius();
				
				if ((this.getTranslateX() > (webX - webRad)) && (this.getTranslateX() < (webX + webRad))) {
					
					if ((this.getTranslateY() > (webY - webRad)) && (this.getTranslateY() < (webY + webRad))) {
						nextTo = true;
					}
				}
			}
		
			
		}
		return nextTo;
	}
	
	
	
	
	@Override
	public void animate(double sceneWidth, double sceneHeight) {
		checkBounce(sceneWidth, sceneHeight);
		// updates dx, dy values according to direction and rotates the image appropriately
		setDXDY();
		
		// updates coordinates of translation based on current dx and dy
		this.setTranslateX(this.getTranslateX() + dx);
		this.setTranslateY(this.getTranslateY() + dy);
	}
	
	
	@Override
	public void checkBounce(double sceneWidth, double sceneHeight) {
		if(this.getTranslateX() - this.getRadius() < 0 ) {
			this.direction = Direction.E;
		}
		
		if (this.getTranslateX() + this.getRadius() > sceneWidth){
			this.direction = Direction.W;
		}
		
		if(this.getTranslateY() - this.getRadius() < 0) {
			this.direction = Direction.S;
		}
		
		if (this.getTranslateY() + this.getRadius() > sceneHeight) {
			this.direction = Direction.N;
		}
	}
	
	public void makeWeb() {
		if (readyToWeb == 100) {
			Web newWeb = new Web(this.getTranslateX(), this.getTranslateY(), 20);
			this.webs.add(newWeb);
			((World) this.getParent()).addWeb(newWeb);
			readyToWeb = 0;
		}
	}

	public ArrayList<Web> getWebs() {
		return webs;
	}

	public void setWebs(ArrayList<Web> webs) {
		this.webs = webs;
	}

	public double getReadyToWeb() {
		return readyToWeb;
	}

	public void setReadyToWeb(double readyToWeb) {
		this.readyToWeb = readyToWeb;
	}
	
	
	
	
}
