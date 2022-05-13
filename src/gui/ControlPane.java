package gui;

import java.util.ArrayList;
import java.util.Set;

import item.SlowPotion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
//import logic.MovingThread;

public class ControlPane extends VBox {
	Button bgmButton;
	Button sfxButton;
	Button newGameButton;
	Button pauseButton;
	Button nextLevelButton;
	Text levelText;
	Text pauseText;
	Text scoreText;
	Text scoreToNextLevelText;
	Text staminaText;
	GamePane gamePane;

	public ControlPane(GamePane gamePane) {
		HBox hBox = new HBox();
		this.gamePane = gamePane;
		this.setAlignment(Pos.CENTER);

		this.setPrefWidth(300);
		this.setPrefHeight(600);
		this.setSpacing(20);
//		this.setPadding(new Insets(8));
//		this.setPrefWidth(500);
		initializeBgm();
		initializeSfx();
		initializeNewGameButton();
		initializePauseModeButton();
		initializeNextLevelButton();
		initializeLevelText();
		initializePauseModeText();
		initializeScoreText();
		initializeScoreToNextLevelText();
		initializeStaminaText();
		
		hBox.getChildren().addAll(sfxButton,bgmButton);
		this.getChildren().addAll(nextLevelButton,staminaText,levelText, scoreText, scoreToNextLevelText, pauseText, hBox, pauseButton, newGameButton);
	}
	
	private void initializeBgm() {
		bgmButton = new Button();
		bgmButton.setGraphic(new ImageView(new Image("bgm.png", 30, 30, false, false)));
		bgmButton.setPrefWidth(150);
//		bgm.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent event) {
//				GameLogic.getInstance().toggleBgm();
//				//turn on sound,turn off sound
//			}
//		});
	}

	private void initializeSfx() {
		sfxButton = new Button();
		sfxButton.setGraphic(new ImageView(new Image("sfx.png", 30, 30, false, false)));
		sfxButton.setPrefWidth(150);
//	sfx.setOnAction(new EventHandler<ActionEvent>() {
//		public void handle(ActionEvent event) {
//			GameLogic.getInstance().toggleSfx();
//			//turn on sound,turn off sound
//		}
//	});
	}

	private void initializeNewGameButton() {
		newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(100);
		newGameButton.setMinWidth(100);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().newGame(2);
				pauseText.setText("Pause mode : OFF");
				levelText.setText("Level : "+GameLogic.getInstance().getLevel());
				scoreText.setText("Score : " + GameLogic.getInstance().getScore());
//				System.out.println(GameLogic.getInstance().isGameEnd());
			}
		});
	}
	
	private void initializePauseModeButton() {
		pauseButton = new Button();
		pauseButton.setGraphic(new ImageView(new Image("pause.png", 50, 50, false, false)));
		pauseButton.setPrefWidth(50);
		newGameButton.setMaxWidth(50);
		newGameButton.setMaxHeight(50);
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (!GameLogic.getInstance().isGameEnd()) {
					GameLogic.getInstance().togglePauseMode();
					if (GameLogic.getInstance().isPause()) {
						pauseText.setText("Pause mode : ON");
					} else {
						pauseText.setText("Pause mode : OFF");
					}
				}
//				Set<Thread> threads = Thread.getAllStackTraces().keySet();
//				System.out.printf("%-15s \t %-15s \t %-15s \t %s\n", "Name", "State", "Priority", "isDaemon");
//				for (Thread t : threads) {
//				    System.out.printf("%-15s \t %-15s \t %-15d \t %s\n", t.getName(), t.getState(), t.getPriority(), t.isDaemon());
//				}
//				System.out.println(GameLogic.getInstance().isGameEnd());
//				System.out.println(GameLogic.getInstance().isPause());
//				System.out.println(GameLogic.getInstance().getGamePane().getSnake().isCrash());
//				for (int i = 0; i < 20; i++) {
//					for (int j = 0; j < 20; j++) {
//						for (Node n : GameLogic.getInstance().getGamePane().getLocationTable()[i][j])
//						{
//							System.out.println("i = " + i + " j = "+ j + "  "+ n.getClass());
//						}
//					}
//				}
//				System.out.println(Mushroom.allMushroom.get(0).getXLocation());
			}
		});
	}
	
	private void initializeNextLevelButton() {
		nextLevelButton = new Button("Click to go to next level");
		nextLevelButton.setFont(Font.font(25));
		nextLevelButton.setVisible(false);
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(nextLevelButton.isVisible()) {
					nextLevelButton.setVisible(false);
					GameLogic.getInstance().setLevel(GameLogic.getInstance().getLevel()+1);
					GameLogic.getInstance().newGame(GameLogic.getInstance().getLevel());
					pauseText.setText("Pause mode : OFF");
					levelText.setText("Level : "+GameLogic.getInstance().getLevel());
					scoreText.setText("Score : " + GameLogic.getInstance().getScore());
					gamePane.getSnake().initializeSnake();
					gamePane.getApple().initialize();
					System.out.println(GameLogic.getInstance().isGameEnd());
				}
			}
		});
	}
	
	private void initializeLevelText() {
		levelText = new Text("Level : " + 1);
		levelText.setFont(Font.font(30));
	}

	private void initializePauseModeText() {
		pauseText = new Text("Pause mode : OFF");
		pauseText.setFont(Font.font(30));
	}
	
	private void initializeScoreText() {
		scoreText = new Text("Score : " + 0);
		scoreText.setFont(Font.font(30));
	}

	private void initializeScoreToNextLevelText() {
		scoreToNextLevelText = new Text("Score to next level : " + 10);
		scoreToNextLevelText.setFont(Font.font(30));
	}
	
	private void initializeStaminaText() {
		staminaText = new Text("Stamina : " + 100);
		staminaText.setFont(Font.font(30));
	}
	
	public Button getBgmButton() {
		return bgmButton;
	}

	public void setBgmButton(Button bgm) {
		this.bgmButton = bgm;
	}

	public Button getSfxButton() {
		return sfxButton;
	}

	public void setSfxButton(Button sfx) {
		this.sfxButton = sfx;
	}

	public Button getNewGameButton() {
		return newGameButton;
	}

	public void setNewGameButton(Button newGameButton) {
		this.newGameButton = newGameButton;
	}
	
	public Button getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(Button pauseButton) {
		this.pauseButton = pauseButton;
	}

	public Button getNextLevelButton() {
		return nextLevelButton;
	}

	public void setNextLevelButton(Button nextLevelButton) {
		this.nextLevelButton = nextLevelButton;
	}
	
	public Text getLevelText() {
		return levelText;
	}
	
	public void setLevelText(String text) {
		levelText.setText(text);
	}
	
	public Text getPauseText() {
		return pauseText;
	}

	public void setPauseText(Text pauseText) {
		this.pauseText = pauseText;
	}

	public Text getScoreText() {
		return scoreText;
	}

	public void setScoreText(Text scoreText) {
		this.scoreText = scoreText;
	}

	public Text getScoreToNextLevelText() {
		return scoreToNextLevelText;
	}

	public void setScoreToNextLevelText(Text scoreToNextLevelText) {
		this.scoreToNextLevelText = scoreToNextLevelText;
	}

	public void setNextLevelButtonText(String text) {
		nextLevelButton.setText(text);
	}


	public void setScoreText(String text) {
		scoreText.setText(text);
	}

	public void setScoreToNextLevelText(String text) {
		scoreText.setText(text);
	}


	public void setLevelText(Text levelText) {
		this.levelText = levelText;
	}

	public Text getStaminaText() {
		return staminaText;
	}

	public void setStaminaText(Text staminaText) {
		this.staminaText = staminaText;
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}

	
	
}
