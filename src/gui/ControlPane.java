package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class ControlPane extends VBox {
	Button bgm;
	Button sfx;
	Text currentScoreText;
	Text scoreToNextLevelText;
	GamePane gamePane;
	public ControlPane(GamePane gamePane) {
		this.gamePane = gamePane;
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);
		this.setSpacing(20);
//		this.setPadding(new Insets(8));
//		this.setPrefWidth(500);
		initializeBgm();
		initializesfx();
		initializeCurrentScoreText();
		initializeScoreToNextLevelText();
		initializeSecureModeButton();
		this.getChildren().addAll(currentScoreText,scoreToNextLevelText,bgm,sfx);
	}

	private void initializeGameText() {
		currentScoreText = new Text("Score : " + gamePane.getTileCount());
		gameText.setFont(Font.font(35));
	}

	public void updateGameText(String text) {
		gameText.setText(text);
	}

	private void initializeNewGameButton() {
		newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(100);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().newGame();
				secureModeButton.setText("Secure mode : OFF");
				gameText.setText("Tiles left : "+GameLogic.getInstance().getTileCount());
				for (MineSweeperSquare m : mineSweeperPane.getAllCells()) {
					m.initializeCellColor();
				}
			}
		});
	}

	private void initializeSecureModeButton() {
		secureModeButton = new Button("Secure mode : OFF");
		secureModeButton.setPrefWidth(150);
		secureModeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().toggleSecureMode();
				if(GameLogic.getInstance().isSecureMode()) {
					secureModeButton.setText("Secure mode : ON");
				}
				else {
					secureModeButton.setText("Secure mode : OFF");
				}
			}
		});
	}

}
