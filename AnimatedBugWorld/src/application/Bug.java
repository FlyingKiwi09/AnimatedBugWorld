package application;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Bug extends Circle {
	
	
	protected float dx;
//	protected final ImageView imageView;

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
	
	// constructors
	// pre-defined bug
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
	
	
	
	
	
	
	
	
	
	
	public void move(World w) {
		int proposedX = this.xPos;
		int proposedY = this.yPos;
		
		if (direction == 'N' && yPos > 1) {
			proposedY--;
		} else if (direction == 'S' && yPos < World.WORLD_HEIGTH-2) {
			proposedY++;
		} else if (direction == 'W' && xPos > 1) {
			proposedX--;
		} else if (direction == 'E' && xPos < World.WORLD_WIDTH-2) {
			proposedX++;
		}
		
		String sense = sense(w, proposedX, proposedY);
		
		// if next to an empty space, move into it
		// use 1 energy
		if (sense.equals("Empty")) {
			this.xPos = proposedX;
			this.yPos = proposedY;
			this.energy--;
		}
	}
	

	
	
	public WorldObject nextToFood(World w) {
		WorldObject nextTo = null;
		
		if (this.eats.equals("Plant")) {
			for (Plant p : w.getPlants()) {
				int plantX = p.getxPos();
				int plantY = p.getyPos();
				// if plant is next to (above, below, left or right of) this bug
				if (((this.xPos == plantX) && (this.yPos == plantY+1 || this.yPos == plantY-1)) || ((this.yPos == plantY) && (this.xPos == plantX+1 || this.xPos == plantX-1))) {
					nextTo = p;
				} 
			}
		}
		
		if (this.eats.equals("Fly")) {
			for (Bug b : w.getBugs()) {
				if (b instanceof Fly ) {
					int flyX = b.getxPos();
					int flyY = b.getyPos();
					// if fly is next to (above, below, left or right of) this bug
					if (((this.xPos == flyX) && (this.yPos == flyY+1 || this.yPos == flyY-1)) || ((this.yPos == flyY) && (this.xPos == flyX+1 || this.xPos == flyX-1))) {
						nextTo = b;
					} 
					
				}
			}
		}
		
		return nextTo;
	}
	
	
	
	/*Checks every object in the world to see if it is in smelling range
	 * If it can smell it, checks it's food (eatsObject)*/
	public char directionToClosestFood(World w) {
		int distanceToObject = 0;
		char directionToObject = ' ';
		
		WorldObject closestFood = null;
		int distanceToClosestFood = 10;
		char directionToCLosestFood = ' ';
		
		// check the bugs for one close enough to be smelt
		for (Bug b: w.getBugs()) {
			// if the object can be smelt
			if (((this.xPos < b.getxPos()+smellRange) && (this.xPos > b.getxPos()-smellRange)) || ((this.yPos < b.getyPos()+smellRange) && (this.yPos > b.getyPos()-smellRange))) {
				// if the object is food
				if (this.eatsObject(b)) {
					// get direction and distance to object
					directionToObject = this.directionToObject(b);
					distanceToObject = this.distanceToObject(b);
					
					if (distanceToObject < distanceToClosestFood) {
						distanceToClosestFood = distanceToObject;
						closestFood = b;
						directionToCLosestFood = directionToObject;
					}
				}
			}
		}
		
		// check the plants for one close enough to be smelt
		for (Plant p: w.getPlants()) {
			// if the object can be smelt
			if (((this.xPos < p.getxPos()+smellRange) && (this.xPos > p.getxPos()-smellRange)) || ((this.yPos < p.getyPos()+smellRange) && (this.yPos > p.getyPos()-smellRange))) {
				// if the object is food
				if (this.eatsObject(p)) {
					// get direction and distance to object
					directionToObject = this.directionToObject(p);
					distanceToObject = this.distanceToObject(p);
					
					if (distanceToObject < distanceToClosestFood) {
						distanceToClosestFood = distanceToObject;
						closestFood = p;
						directionToCLosestFood = directionToObject;
					}
				}
			}
		}
		return directionToCLosestFood;
	}
	
	public char directionToObject(WorldObject WO) {
		char directionToObject = ' ';
		
		if (this.xPos < WO.getxPos()) {
			directionToObject = 'E';
		} else if (this.xPos > WO.getxPos()){
			directionToObject = 'W';
		} else if (this.yPos > WO.getyPos()) {
			directionToObject = 'S';
		} else if (this.yPos < WO.getyPos()) {
			directionToObject = 'N';
		}
		
		return directionToObject;
	}
	
	
	public int distanceToObject(WorldObject WO) {
		int distanceToObject = 0;
		
		if (this.xPos < WO.getxPos()) {
			distanceToObject = WO.getxPos() - this.xPos;
		} else if (this.xPos > WO.getxPos()){
			distanceToObject = this.xPos - WO.getxPos();
		} else if (this.yPos > WO.getyPos()) {
			distanceToObject = this.yPos - WO.getyPos();
		} else if (this.yPos < WO.getyPos()) {
			distanceToObject = WO.getxPos() - this.xPos;
		}
		
		return distanceToObject;
	}
	
	public boolean eatsObject(WorldObject WO) {
		boolean eatsObject = false;
		if (WO.getType().equals(this.eats)) {
			eatsObject = true;
		}
		return eatsObject;
	}

	
	public String sense(World w, int proposedX, int proposedY) {
		
		String sensed = "Empty";
		
		// check for obstacle
		if (w.checkForObstacle(proposedX, proposedY)) {
			sensed = "Obstable";
		}
		
		//check for bug
		if (w.checkForBug(proposedX, proposedY)) {
			sensed = "Bug";
		}
		
		//check for plant
		if (w.checkForPlant(proposedX, proposedY)) {
			sensed = "Plant";
		}
		
		return sensed;
	}
	
	
//	public void move100() {
//		for (int i = 0; i < 100; i++) {
//			setRandomDirection();
//			move();
//		}
//	}

	// getters and setters
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
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
	
	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	// to string
	public String toString() {
		return (this.ID + " " + this.species + " " + this.name);
	}
	
	// to text
	public String toText() {
		String t = (this.ID + " " + this.species + " " + this.symbol + " " + this.name + " " + this.xPos + " " + this.yPos + " " + this.energy);
		return t;
	}
	
	public void eaten() {
		this.energy--;
	}
	
	public void eats() {
		this.energy+=4;
		this.lastEaten = 0;
	}
	

}
