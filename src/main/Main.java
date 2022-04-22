package main;
import java.util.ArrayList;

import gui.ControlPane;
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
import logic.GameLogic;
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
		root.setMinWidth(1200);
		root.setSpacing(10);
//		root.setPadding(new Insets(10));
		root.setPrefHeight(600);
		GamePane gamePane = new GamePane(60,60);
		ControlPane controlPane = new ControlPane(gamePane);
		root.getChildren().add(gamePane);
		root.getChildren().add(controlPane);
		GameLogic.getInstance().setControlPane(controlPane);
		GameLogic.getInstance().getMoving().start();
		Scene scene = new Scene(root, 920, 600);

//		game = new Thread(() -> {
//			while ((!GameLogic.getInstance().isGameEnd())) {
//				if(!GameLogic.getInstance().isPause()) {
//					Platform.runLater(new Runnable(){
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							gamePane.getSnake().requestFocus();
//						}
//					});
//					gamePane.getSnake().move();
//					gamePane.checkEat();
//					if(gamePane.getSnake().isCrash()) {
//						GameLogic.getInstance().setGameEnd(true);
//					}
//				}
//				try {
//					Thread.sleep(Head.speed); // move by using move() every 0.2 second
//				} catch (Exception ex) {
//				}
//			}
//		});
//		game.start();
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