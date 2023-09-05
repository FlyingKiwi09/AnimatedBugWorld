package application;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Bug extends Circle {
	
	protected float dx;
	protected float dy;
	protected int energy;
	protected int ID;
	protected static int bugCount = 0;
	protected Direction direction;
	protected int smellRange;
	protected String eats;
	protected boolean dead;
	protected int lastEaten;
	protected boolean isStopped;
	protected int stoppedCount;
	
	// constructor
	public Bug(double x, double y, double rad) {
		super(0,0, rad);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.energy= 10;
		bugCount++;
		this.ID = bugCount;
		this.smellRange = 2;
		this.eats = "";
		this.lastEaten = 80;
		this.dead = false;
		this.isStopped = false;
		setRandomDirection();
		setDXDY();
	}
	
	// empty method, overwritten in different subclasses
	public void update(World world) {

	}
	
	// checks if the bug is next to a plant, returns the plant if it is and null if not
	public Plant nextToPlant(World w) {
		Plant nextTo = null;
		for (Plant p : w.getPlants()) {
			double plantX = p.getCenterX();
			double plantY = p.getCenterY();
			double plantRad = p.getRadius();
			
			if ((this.getTranslateX() > (plantX - plantRad)) && (this.getTranslateX() < (plantX + plantRad))) {
				
				if ((this.getTranslateY() > (plantY - plantRad)) && (this.getTranslateY() < (plantY + plantRad))) {
					nextTo = p;
				}
			}
		}
		return nextTo;
	}
	
	
	// empty method, overwritten in different subclasses
	public void eat(Plant toEat) {
		
	}

	
	public void animate(double sceneWidth, double sceneHeight) {
		
		// checks if the bug is at an edge and sets a new direction if it is
		checkBounce(sceneWidth, sceneHeight);
		
		// updates dx, dy values according to direction and rotates the image appropriately
		setDXDY();
		
		// updates coordinates of translation based on current dx and dy
		this.setTranslateX(this.getTranslateX() + dx);
		this.setTranslateY(this.getTranslateY() + dy);
	}
	
	
	public void checkBounce(double sceneWidth, double sceneHeight) {
		/* if the bug reaches a boundary set it's direction to one of the three possible "bounce" directions
		 * e.g. if it's at the left hand edge, it can go NE, E or SE
		*/
		
		// random number: 0, 1 or 2 to set one of the 3 possible bounce direcitons
		int dir = (int)(0 + Math.random()*3);
		
		
		// if at the left hand edge, it can go NE, E or SE
		if(this.getTranslateX() - this.getRadius() < 0 ) {
			switch(dir)
			 {
				 case 0: 
					 this.direction = Direction.NE;
					 break;
				 case 1: 
					 this.direction = Direction.E;
					 break;
				 case 2: 
					 this.direction = Direction.SE;
					 break;
			 }
		}
		
		
		// if at the right hand edge, it can go NW, W or SW
		if (this.getTranslateX() + this.getRadius() > sceneWidth){
			switch(dir)
			 {
				 case 0: 
					 this.direction = Direction.NW;
					 break;
				 case 1: 
					 this.direction = Direction.W;
					 break;
				 case 2: 
					 this.direction = Direction.SW;
					 break;
			 }
		}
			
		
		// if at the top edge, it can go SW, S or SE
		if(this.getTranslateY() - this.getRadius() < 0) {
			switch(dir)
			 {
				 case 0: 
					 this.direction = Direction.SW;
					 break;
				 case 1: 
					 this.direction = Direction.S;
					 break;
				 case 2: 
					 this.direction = Direction.SE;
					 break;
			 }
		}
		
		
		// if at the bottom edge, it can go NW, N or NE
		if (this.getTranslateY() + this.getRadius() > sceneHeight) {
			
			switch(dir)
			 {
				 case 0: 
					 this.direction = Direction.NW;
					 break;
				 case 1: 
					 this.direction = Direction.N;
					 break;
				 case 2: 
					 this.direction = Direction.SE;
					 break;
			 }
		}
	}
	
	// sets the differnce x (dx) and differnce y (dy) values based on the current direction
	public void setDXDY() {
		switch (this.direction) {
			case N:
				this.dx = 0;
				this.dy = -1.5f;
				this.setRotate(0);
				break;
			 case NE: 
				 this.dx = 1.5f;
				 this.dy = -1.5f;
				 this.setRotate(45);
				 break;
			 case E: 
				 this.dx = 1.5f;
				 this.dy = 0;
				 this.setRotate(90);
				 break;
			 case SE: 
				 this.dx = 1.5f;
				 this.dy = 1.5f;
				 this.setRotate(135);
				 break;
			 case S: 
				 this.dx = 0;
				 this.dy = 1.5f;
				 this.setRotate(180);
				 break;
			 case SW: 
				 this.dx = -1.5f;
				 this.dy = 1.5f;
				 this.setRotate(225);
				 break;
			 case W: 
				 this.dx = -1.5f;
				 this.dy = 0;
				 this.setRotate(270);
				 break;
			 case NW: 
				 this.dx = -1.5f;
				 this.dy = -1.5f;
				 this.setRotate(315);
				 break;
				 
		}
	}

	// sets a random direction (one of 8 possibilities)
	public void setRandomDirection() {
		
		int dir = (int)(0 + Math.random()*8);
		
		 switch(dir)
		 {
			 case 0: 
				 this.direction = Direction.N;
				 break;
			 case 1: 
				 this.direction = Direction.NE;
				 break;
			 case 2: 
				 this.direction = Direction.E;
				 break;
			 case 3: 
				 this.direction = Direction.SE;
				 break;
			 case 4: 
				 this.direction = Direction.S;
				 break;
			 case 5: 
				 this.direction = Direction.SW;
				 break;
			 case 6: 
				 this.direction = Direction.W;
				 break;
			 case 7: 
				 this.direction = Direction.NW;
				 break;
		 }
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getID() {
		return ID;
	}

	public static int getBugCount() {
		return bugCount;
	}

	public static void setBugCount(int bugCount) {
		Bug.bugCount = bugCount;
	}
	
	
	public void eaten() {
		this.energy--;
	}
	
	public void eats() {
		this.energy+=4;
		this.lastEaten = 0;
	}
	

}
