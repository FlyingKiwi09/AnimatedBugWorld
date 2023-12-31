package application;
	

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	private World world;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private double screenHeight = dimension.getHeight();
    private double screenWidth = dimension.getWidth();

	
	@Override
	public void start(Stage primaryStage) {
		world = new World();
		BorderPane border = new BorderPane();
		
		border.setCenter(world);
		world.setBackground(new Background(new BackgroundFill(Color.OLIVEDRAB, null, null)));
		HBox top = new HBox();
		border.setTop(top);
		top.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		top.setPrefHeight(60);
		top.setAlignment(Pos.CENTER_LEFT);
		
		Font buttonFont = new Font(15);
		Background buttonBackground = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), null));
		
	// controls
		// restart button
		Button restart = new Button("Restart");
		top.getChildren().add(restart);
		top.setMargin(restart, new Insets(5));
		restart.setFont(buttonFont);
		restart.setBackground(buttonBackground);	
		restart.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				restart.setScaleX(1.08);
				restart.setScaleY(1.08);
			}
			
		});
		
		restart.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				restart.setScaleX(1.0);
				restart.setScaleY(1.0);
			}
			
		});
		
		// pause button
		Button pause = new Button("Pause");
		top.getChildren().add(pause);
		top.setMargin(pause, new Insets(5));
		pause.setFont(buttonFont);
		pause.setBackground(buttonBackground);
		
		pause.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				pause.setScaleX(1.08);
				pause.setScaleY(1.08);
			}
			
		});
		
		pause.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				pause.setScaleX(1.0);
				pause.setScaleY(1.0);
			}
			
		});
		

		// play button
		Button play = new Button("Play");
		top.getChildren().add(play);
		top.setMargin(play, new Insets(5));
		play.setFont(buttonFont);
		play.setBackground(buttonBackground);
		
		play.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				play.setScaleX(1.08);
				play.setScaleY(1.08);
			}
			
		});
		
		play.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				play.setScaleX(1.0);
				play.setScaleY(1.0);
			}
			
		});
		
		
		
		Label progressLabel = new Label("Web Status Bar");
		top.getChildren().add(progressLabel);
		top.setMargin(progressLabel, new Insets(5,5,5,50));
		
		ProgressBar webProgress = new ProgressBar(0);
		webProgress.setPrefWidth(300);
		top.getChildren().add(webProgress);
		top.setMargin(webProgress, new Insets(5));
		
		Label messages = new Label("");
		top.getChildren().add(messages);
		top.setMargin(messages, new Insets(5));
		
		

//		Slider speed = new Slider(1, 100, 16);
//		top.getChildren().add(speed);
//		top.setMargin(speed, new Insets(5));
//		speed.setPrefWidth(200);
//		speed.setBlockIncrement(1);
//		
		
		

		final Scene scene = new Scene(border);

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
		
		
		Timeline timeline = new Timeline();
		
		
		// animation of bugs
		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// animate each of the balls in the arraylist
				world.update();
				webProgress.setProgress(world.getSpider().getReadyToWeb()/100);
				if (world.getSpider().dead) {
					timeline.stop();
					messages.setText("GAME OVER: YOU LOSE!");
				}
				
				if (checkForWin()) {
					timeline.stop();
					messages.setText("GAME OVER: YOU WIN!");
				}
				
				
			}
		});
		
		// play frame
		
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
		
		restart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				timeline.stop();
				start(primaryStage);
				
			}
			
		});
		
		
		// set scene and show primaryStage
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Hello Annimation");
		primaryStage.setScene(scene);
		primaryStage.setWidth(screenWidth);
		primaryStage.setHeight(screenHeight-40);
//		primaryStage.setMaximized(true);
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
	

		
	
	private boolean checkForWin() {
		for(Bug bug: world.bugs) {
			if (bug instanceof Fly) {
				if (!((Fly) bug).isCaught()) {
					return false;
				}
			}
		}
		return true;
	}
	

	
	

}
