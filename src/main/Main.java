package main;

import java.util.ArrayList;

import gui.ControlPane;
import gui.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameLogic;
import logic.MovingThread;
import snake.Head;

public class Main extends Application {
//	private Pane root = new Pane();
//	private ArrayList<Body> snake = new ArrayList<Body>();
	private GamePane gamePane = new GamePane(60, 60);
//	private int d = snake.getSnake().get(0).getDirection();
	Thread game;

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Scene 1
		GridPane root1 = new GridPane();

		Button start = new Button("Start");
		Button howToPlay = new Button("How to play");
		Button close = new Button("X");
		Text htp = new Text("How to play");
		close.setVisible(false);
		htp.setVisible(false);

		root1.setAlignment(Pos.CENTER);
		root1.add(start, 10, 10);
		root1.add(howToPlay, 10, 20);
		root1.add(htp, 7, 7, 10, 20);
		root1.add(close, 17, 20);

		Scene scene1 = new Scene(root1, 920, 600);

		// Scene 2
		HBox root2 = new HBox();
		root2.setMinWidth(1200);
		root2.setSpacing(10);
//		root2.setPadding(new Insets(10));
		root2.setPrefHeight(600);

		GamePane gamePane = new GamePane(60, 60);
		ControlPane controlPane = new ControlPane(gamePane);
		Button back = new Button("Back to main menu");

		controlPane.getChildren().add(back);
		root2.getChildren().add(gamePane);
		root2.getChildren().add(controlPane);

		GameLogic.getInstance().setControlPane(controlPane);
		MovingThread movingThread = new MovingThread();
		movingThread.start();
		Scene scene2 = new Scene(root2, 920, 600);

		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().getControlPane().getNewGameButton().fire();
				GameLogic.getInstance().setPause(false);
				primaryStage.setScene(scene2);
			}
		});

		howToPlay.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				htp.setVisible(true);
				close.setVisible(true);
			}
		});
		
		close.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				htp.setVisible(false);
				close.setVisible(false);
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().setPause(true);
				primaryStage.setScene(scene1);
			}
		});

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
		primaryStage.setTitle("Snake Game");
		primaryStage.setScene(scene1);
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