package main;
import java.util.ArrayList;

import gui.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import snake.Head;

public class Main extends Application {
//	private Pane root = new Pane();
//	private ArrayList<Body> snake = new ArrayList<Body>();
	private GamePane gamePane = new GamePane(60,60);
//	private int d = snake.getSnake().get(0).getDirection();
	Thread game;

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox root = new HBox();
		GamePane gamePane = new GamePane(60,60);
//		private ControlPane controlPane = new ControlPane();
		root.getChildren().add(gamePane);
//		root.getChildren().add(controlPane);
		Scene scene = new Scene(root, 600, 600);

		game = new Thread(() -> {
			while (!gamePane.getGameEnd()) {
				gamePane.getSnake().move();
				gamePane.checkEat();
				if(gamePane.getSnake().isCrash()) {
					gamePane.setGameEnd(true);
				}
				try {
					Thread.sleep(Head.speed); // move by using move() every 0.2 second
				} catch (Exception ex) {
				}
			}
		});
		game.start();
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(600);
		primaryStage.setMaxHeight(600);
		primaryStage.setMaxWidth(600);
//        primaryStage.setOnCloseRequest(event -> isDone = true);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		primaryStage.show();
		gamePane.getSnake().requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}
}