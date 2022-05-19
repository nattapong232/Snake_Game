package main;

import gui.ControlPane;
import gui.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameLogic;

public class Main extends Application {
	public void start(Stage primaryStage) throws Exception {
		// Scene 1
		StackPane root1 = new StackPane();
		Button start = new Button("Start");
		start.setMinSize(110, 110);
		ImageView htp = new ImageView(new Image(ClassLoader.getSystemResource("background/HowToPlayBG.png").toString()));
		
		htp.setVisible(false);

		root1.getChildren().add(start);
		root1.getChildren().add(htp);
		root1.setPrefWidth(900);
		root1.setPrefHeight(600);
		
		Scene scene1 = new Scene(root1, 900, 600);
		scene1.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
		
		// Scene 2
		HBox root2 = new HBox();
		root2.setPrefWidth(900);
		root2.setPrefHeight(600);

		GamePane gamePane = new GamePane();
		ControlPane controlPane = new ControlPane(gamePane);
		Button back = new Button("Back to main menu");

		controlPane.getChildren().add(back);
		root2.getChildren().add(gamePane);
		root2.getChildren().add(controlPane);
		
		GameLogic.getInstance().setGamePane(gamePane);
		GameLogic.getInstance().setControlPane(controlPane);
		
		Scene scene2 = new Scene(root2, 900, 600);
		scene2.getRoot().setStyle("-fx-font-family: 'Jungle Adventurer'");
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().getControlPane().getNewGameButton().fire();
				GameLogic.getInstance().setPause(false);
				primaryStage.setScene(scene2);
			}
		});
		
		primaryStage.setResizable(false);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().setPause(true);
				primaryStage.setScene(scene1);
			}
		});

		root1.setOnKeyPressed(event -> {
			KeyCode k = event.getCode();
			switch (k) {
			case H:
				if (!htp.isVisible()) {
					htp.setVisible(true);
				}
				else {
					htp.setVisible(false);
				}
				break;
			}
		});
		
		primaryStage.setTitle("Snake the Explorer");
		primaryStage.setScene(scene1);
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(900);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
	
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}