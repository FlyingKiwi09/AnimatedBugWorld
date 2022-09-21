package application;
	
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane border = new BorderPane();
		World world = new World();
		border.setCenter(world);
		HBox bottom = new HBox();
		border.setBottom(bottom);
		
		// create new bugs and plants, add them to their group and ArrayList
		for (int i = 0; i < 3; i++) {
			
			Plant newPlant = new Plant(50*(i+1), 50*(i+1), 10);
			world.getPlants().add(newPlant);
			world.getChildren().add(newPlant);
			
			Ladybug newLB = new Ladybug(50*(i+1), 50*(i+1));
			world.getBugs().add(newLB);
			world.getChildren().add(newLB);	
			
			Fly newBug = new Fly(50*(i+1), 50*(i+1));
			world.getBugs().add(newBug);
			world.getChildren().add(newBug);	
		}
		

		// controls
		Button pause = new Button("Pause");
		bottom.getChildren().add(pause);
		
		Button play = new Button("play");
		bottom.getChildren().add(play);
		

		final Scene scene = new Scene(border,400,400);
		
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
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
