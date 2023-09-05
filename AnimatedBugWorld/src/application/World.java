package application;
import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class World extends Pane{
	
	float x = 50f, y = 50f;
	final ArrayList<Bug> bugs = new ArrayList<Bug>();
	final ArrayList<Plant> plants = new ArrayList<Plant>();
	private Spider spider;
	private int width = 1250;
	private int height = 650;
	private double counter = 0;
	

//	private final int BUG_QUANTITY = 3;
//	private final int PLANT_QUANTITY = 3;
//	private final int OBSTACLE_QUANTITY = 3;
//	private ArrayList<WorldObject> objectList;
//	private ArrayList<Bug> bugs;
//	private ArrayList<Plant> plants;
//	private ArrayList<Obstacle> obstacles;
	
	// constructor
	public World() {
		super();
		// generate creatures
		generatePlant(5); 
		generateLadybug(5);
		generateFly(5);
		
		spider = new Spider(50, 50, 30);

		this.getChildren().add(spider);
	
	}


	public void update() {
		for (int i = 0; i < bugs.size(); i++) {					
			bugs.get(i).update(this);
		}
		
		for (int i = 0; i < plants.size(); i++) {
			plants.get(i).grow();
		}
		spider.update(this);
		counter++;
		if (counter == 1000) {
			generatePlant(1); 
			generateLadybug(1);
			generateFly(1);
			counter = 0;
		}
	}
	
	public ArrayList<Bug> getBugs() {
		return bugs;
	}


	public ArrayList<Plant> getPlants() {
		return plants;
	}


	public Spider getSpider() {
		return spider;
	}


	public void setSpider(Spider spider) {
		this.spider = spider;
	}
	
	
	public void addWeb(Web web) {
		this.getChildren().add(web);
	}
	
	
	private void generatePlant(int numberOfPlants) {
		for (int i = 0; i < numberOfPlants; i++) {
			int newX = getRandomNumber(40, width-40);
			int newY = getRandomNumber(40, height-40);
			Plant newPlant = new Plant(newX, newY, 10);
			plants.add(newPlant);
			this.getChildren().add(newPlant);
		}
	}
	
	
	private void generateLadybug(int numberOfLadyBugs) {
		for (int i = 0; i < numberOfLadyBugs; i++) {
			int newX = getRandomNumber(40, width);
			int newY = getRandomNumber(40, height);
			Ladybug newLB = new Ladybug(newX, newY, 10);
			bugs.add(newLB);
			this.getChildren().add(newLB);
		}
	}
	
	private void generateFly(int numberOfFlys) {
		for (int i = 0; i < numberOfFlys; i++) {
			int newX = getRandomNumber(40, width);
			int newY = getRandomNumber(40, height);
			Fly newBug = new Fly(newX, newY, 10);
			bugs.add(newBug);
			this.getChildren().add(newBug);
		}
	}
	
	private int getRandomNumber(int minimum, int maximum) {
		return (int) (Math.random() * (maximum)) + minimum;
	}
	
	
//	/*
//	public <T extends Bug> ArrayList<T> get() {
//		ArrayList<T> temp = new ArrayList<T>();
//		
//		for (int loop = 0; loop < this.bugs.size(); loop++)  {
//			Bug b = this.bugs.get(loop);
//				temp.add(b);
//			}
//		}
//		
//		return temp;
//	}
//	
//	public void test() {
//		this.<Ant>get();
//		this.<Spider>get();
//	}
//	*/
//	
//	public void createBugs() {
//		for (int i = 0; i < BUG_QUANTITY; i++) {
//			Bug newBug = null;
//			// random int to determine bugType
//			int n = (int)Math.floor(1+Math.random()*3);
//			switch (n) {
//				case 1:
//					newBug = new Fly();
//					break;
//				case 2:
//					newBug = new Ant();
//					break;
//				case 3:
//					newBug = new Spider();
//					break;
//			}
//			// check newBug has been created
//			// if the position of the new bug clashes with an existing bug,
//			// update it's position until there is no clash
//			if (newBug != null) {
//				while (checkForClash(newBug.getxPos(), newBug.getyPos())) {
//					newBug.setRandomDirection();
//					newBug.move(this);
//				}
//				bugs.add(newBug);
//			}
//		}
//	}
//	
//	public void createPlants() {
//		for (int i = 0; i < PLANT_QUANTITY; i++) {
//			Plant newPlant = new Plant();
//			while (checkForClash(newPlant.getxPos(), newPlant.getyPos())) {
//				newPlant.setxPos(getRandomX());
//				newPlant.setyPos(getRandomY());
//			}
//			plants.add(newPlant);
//		}
//	}
//	
//	public void createObstacles() {
//		for (int i = 0; i < OBSTACLE_QUANTITY; i++) {
//			Obstacle newObstacle = new Obstacle();
//			while (checkForClash(newObstacle.getxPos(), newObstacle.getyPos())) {
//				newObstacle.setxPos(getRandomX());
//				newObstacle.setyPos(getRandomY());
//			}
//			obstacles.add(newObstacle);
//		}
//	}
//	
//	public void drawWorld() {
//		for (int j = 0; j < WORLD_HEIGTH; j++) {
//			String lineString = "|";
//			for (int i = 0; i < WORLD_WIDTH; i++) {
//				if (j == 0 || j == WORLD_HEIGTH-1) {
//					lineString += "-";
//				} else {
//					String s = " ";
//					for (Bug b : bugs) {
//						if (b.getxPos() == i && b.getyPos() == j) {
//							s = "" + b.getSymbol();
//						}
//					}
//					for (Plant p : plants) {
//						if (p.getxPos() == i && p.getyPos() == j) {
//							s = "" + p.getSize();
//						}
//					}
//					for (Obstacle o : obstacles) {
//						if (o.getxPos() == i && o.getyPos() == j) {
//							s = "" + o.getSymbol();
//						}
//					}
//					lineString += s;
//				}
//			}
//			lineString += "|";
//			System.out.println(lineString);
//		}
//	}
//	
//	public void updateWorld() {
//		// update bugs position
//		for (Bug b : bugs) {
//			// check if the bug has run out of energy
//			if (b.energy <= 0) {
//				b.dead = true; // if so, it's dead
//				b.symbol = 'X'; // change it's symbol
//			}
//			
//			// if the bug is alive, it moves and eats
//			if (!(b.dead)) {
//				// reset the direction of the bug
//				char direction = ' ';
//				
//				// if it's been a while since the bug ate, it looks for food.
//				if (b.getEnergy() <= 20) {
//					direction = b.directionToClosestFood(this);
//					if (direction != ' ') {
//						b.setDirection(direction);
//					} else {
//						b.setRandomDirection();
//					}
//					
//				} else {
//					b.setRandomDirection();
//				}
//				
//				b.move(this);// move bug
//				
//				// check if the bug has moved next to a food item, if so, eat it
//				WorldObject foodToEat = b.nextToFood(this);
//				if (!(foodToEat == null)) {
//					foodToEat.eaten();
//					b.eats();
//				}
//			}
//		}
//		
//		// grow plants
//		for (Plant p: plants) {
//			// give plants a 1/3 chance of growing
//			int n = (int)Math.floor(1+Math.random()*3);
//			if (n == 1) {
//				p.grow();
//			}
//		}
//	}
//	
//	public boolean checkForClash(int x, int y) {
//		boolean clash = false;
//
//		if (checkForBug(x, y) || checkForObstacle(x, y) || checkForPlant(x, y)) {
//			clash = true;
//		}
//		return clash;
//	}
//	
//	public boolean checkForBug(int x, int y) {
//		boolean bug = false;
//		for (Bug b: bugs) {
//			if ((x == b.getxPos() && y == b.getyPos())) {
//				bug = true;
//			}
//		}
//		return bug;
//	}
//	
//	public boolean checkForObstacle(int x, int y) {
//		boolean obstacle = false;
//		for (Obstacle o: obstacles) {
//			if ((x == o.getxPos() && y == o.getyPos())) {
//				obstacle = true;
//			}
//		}
//		return obstacle;
//	}
//	
//	public boolean checkForPlant(int x, int y) {
//		boolean plant = false;
//		for (Plant p: plants) {
//			if ((x == p.getxPos() && y == p.getyPos())) {
//				plant = true;
//			}
//		}
//		return plant;
//	}
//
//	public ArrayList<WorldObject> getObjectList() {
//		return objectList;
//	}
//
//	public void setObjectList(ArrayList<WorldObject> objectList) {
//		this.objectList = objectList;
//	}
//
//	public ArrayList<Bug> getBugs() {
//		return bugs;
//	}
//
//	public void setBugs(ArrayList<Bug> bugs) {
//		this.bugs = bugs;
//	}
//
//	public ArrayList<Plant> getPlants() {
//		return plants;
//	}
//
//	public void setPlants(ArrayList<Plant> plants) {
//		this.plants = plants;
//	}
//
//	public ArrayList<Obstacle> getObstacles() {
//		return obstacles;
//	}
//
//	public void setObstacles(ArrayList<Obstacle> obstacles) {
//		this.obstacles = obstacles;
//	}
//	
//	public int getRandomX() {
//		return (int)Math.floor(1+ Math.random()*(World.WORLD_WIDTH-2));
//	}
//	
//	public int getRandomY() {
//		return (int)Math.floor(1+ Math.random()*(World.WORLD_HEIGTH-2));
//	}
	
}
