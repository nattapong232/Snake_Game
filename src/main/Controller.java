package main;

import java.net.URL;
import java.util.ResourceBundle;

import gui.ControlPane;
import gui.GamePane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.GameLogic;

public class Controller implements Initializable {

	private Parent root1;
	private Stage stage;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button howToPlayButton;
	
	@FXML
	private Button closeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	public void startGame(ActionEvent event) {
		GameLogic.getInstance().getControlPane().getNewGameButton().fire();
        GameLogic.getInstance().setPause(false);
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
		Stage window = (Stage) startButton.getScene().getWindow();
		window.setScene(scene2);
	}
	
}
