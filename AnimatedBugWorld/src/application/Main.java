package application;
	

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {
	World world = new World();
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane border = new BorderPane();
		
		border.setCenter(world);
		world.setBackground(new Background(new BackgroundFill(Color.OLIVEDRAB, null, null)));
		HBox bottom = new HBox();
		border.setBottom(bottom);
		bottom.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null)));
		
		// create new bugs and plants, add them to their group and ArrayList
		for (int i = 0; i < 3; i++) {
			
			Plant newPlant = new Plant(50*(i+1), 50*(i+1), 10);
			world.getPlants().add(newPlant);
			world.getChildren().add(newPlant);
			
			Ladybug newLB = new Ladybug(50*(i+1), 50*(i+1), 10);
			world.getBugs().add(newLB);
			world.getChildren().add(newLB);	
			
			Fly newBug = new Fly(50*(i+1), 50*(i+1), 10);
			world.getBugs().add(newBug);
			world.getChildren().add(newBug);
			
			
			
		}
		
		Spider newSpider = new Spider(50, 50, 30);
		world.setSpider(newSpider);
		world.getChildren().add(newSpider);
		

		// controls
		Button pause = new Button("Pause");
		bottom.getChildren().add(pause);
		bottom.setMargin(pause, new Insets(5));
		
		Button play = new Button("play");
		bottom.getChildren().add(play);
		bottom.setMargin(play, new Insets(5));
		
		Label speedLabel = new Label("Speed");
		bottom.getChildren().add(speedLabel);
		bottom.setMargin(speedLabel, new Insets(5));

		Slider speed = new Slider(100, 0, 16);
		bottom.getChildren().add(speed);
		bottom.setMargin(speed, new Insets(5));
		speed.setShowTickMarks(true);
		speed.setPrefWidth(200);
		speed.setMajorTickUnit(10);
		speed.setBlockIncrement(1);
		
		
		

		final Scene scene = new Scene(border,400,400);

		scene.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getCharacter());
				
				if (e.getCharacter().equals("w")) {
			        world.getSpider().setDirection(Direction.N);
			        System.out.println("UP");
			    }
			    
			    if (e.getCharacter().equals("s")) {
			        world.getSpider().setDirection(Direction.S);
			        System.out.println("Down");
			    }
			    
			    if (e.getCharacter().equals("a")) {
			        world.getSpider().setDirection(Direction.W);
			    }
			    
			    if (e.getCharacter().equals("d")) {
			        world.getSpider().setDirection(Direction.E);
			    }
			    
			    
			    if (e.getCharacter().equals("b")) {
			        world.getSpider().makeWeb();
			    }
			}
			
		});
		//world.setOnKeyTyped( e -> keyTyped(e) );
		
		// animation of bugs
		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// animate each of the balls in the arraylist
				world.update();
			
			}
		});
		
		// play frame
		Timeline timeline = new Timeline();
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
		
		// pause
		pause.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				timeline.pause();
			}
			
		});
		
		//play		
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				timeline.play();
			}
			
		});
		
		


	
		
		
		// set scene and show primaryStage
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Hello Annimation");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getCharacter());
	
		if (e.getCode().equals(KeyCode.UP)) {
	        world.getSpider().setDirection(Direction.N);
	        System.out.println("UP");
	    }
	    
	    if (e.getCode() == KeyCode.DOWN) {
	        world.getSpider().setDirection(Direction.S);
	        System.out.println("Down");
	    }
	    
	    if (e.getCode() == KeyCode.LEFT) {
	        world.getSpider().setDirection(Direction.W);
	    }
	    
	    if (e.getCode() == KeyCode.RIGHT) {
	        world.getSpider().setDirection(Direction.E);
	    }
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
