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
	
	float x = 50f, y = 50f;
	final ArrayList<Bug> bugs = new ArrayList<Bug>();
	final ArrayList<Plant> plants = new ArrayList<Plant>();
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane border = new BorderPane();
		Pane center = new Pane();
		border.setCenter(center);
		HBox bottom = new HBox();
		border.setBottom(bottom);
		
		// create new bugs and plants, add them to their group and ArrayList
		for (int i = 0; i < 3; i++) {
			
			Plant newPlant = new Plant(50*(i+1), 50*(i+1), 10);
			plants.add(newPlant);
			center.getChildren().add(newPlant);
			
			Ladybug newLB = new Ladybug(x*(i+1), y*(i+1));
			bugs.add(newLB);
			center.getChildren().add(newLB);	
			
			Fly newBug = new Fly(x*(i+1), y*(i+1));
			bugs.add(newBug);
			center.getChildren().add(newBug);	
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
				for (int i = 0; i < bugs.size(); i++) {					
					bugs.get(i).update(center.getWidth(), center.getHeight());
				}
				
				for (int i = 0; i < plants.size(); i++) {
					plants.get(i).grow();
				}
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
