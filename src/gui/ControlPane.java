package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class ControlPane extends VBox {
	private Button bgmButton;
	private Button sfxButton;
	private Button newGameButton;
	private Button pauseButton;
	private Button nextLevelButton;
	private Text levelText;
	private Text pauseText;
	private Text scoreText;
	private Text scoreToNextLevelText;
	private Text staminaText;

	public ControlPane(GamePane gamePane) {
		HBox hBox = new HBox();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);
		this.setPrefHeight(600);
		this.setSpacing(20);
		initializeBgmButton();
		initializeSfxButton();
		initializeNewGameButton();
		initializePauseModeButton();
		initializeNextLevelButton();
		initializeLevelText();
		initializePauseModeText();
		initializeScoreText();
		initializeScoreToNextLevelText();
		initializeStaminaText();

		hBox.getChildren().addAll(sfxButton, bgmButton);
		this.getChildren().addAll(nextLevelButton, staminaText, levelText, scoreText, scoreToNextLevelText, pauseText,
				hBox, pauseButton, newGameButton);
	}

	private void initializeBgmButton() {
		bgmButton = new Button();
		ImageView bgmOn = new ImageView(new Image("control/bgm/BGMOn.png", 30, 30, false, false));
		ImageView bgmOff = new ImageView(new Image("control/bgm/BGMOff.png", 30, 30, false, false));
		bgmButton.setGraphic(bgmOn);
		bgmButton.setPrefWidth(150);
		
		bgmButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (!GameLogic.getInstance().isGameEnd()) {
					GameLogic.getInstance().toggleBgm();
					// turn on sound,turn off sound
					if (GameLogic.getInstance().isBgmOn()) {
						GameLogic.getBgmSound().play();
						bgmButton.setGraphic(bgmOn);
					} else {
						GameLogic.getBgmSound().pause();
						bgmButton.setGraphic(bgmOff);
					}
				}
			}
		});
	}

	private void initializeSfxButton() {
		sfxButton = new Button();
		ImageView sfxOn = new ImageView(new Image("control/sfx/SFXOn.png", 30, 30, false, false));
		ImageView sfxOff = new ImageView(new Image("control/sfx/SFXOff.jpg", 30, 30, false, false));
		sfxButton.setGraphic(sfxOn);
		sfxButton.setPrefWidth(150);
		sfxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (!GameLogic.getInstance().isGameEnd()) {
					GameLogic.getInstance().toggleSfx();
					if (GameLogic.getInstance().isSfxOn()) {
						sfxButton.setGraphic(sfxOn);
					} else {
						sfxButton.setGraphic(sfxOff);
					}
				}		
			}
		});
	}

	private void initializeNewGameButton() {
		newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(100);
		newGameButton.setMinWidth(100);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameLogic.getInstance().newGame(1);
				pauseText.setText("Pause mode: OFF");
				levelText.setText("Level: " + GameLogic.getInstance().getLevel());
				scoreText.setText("Score: " + GameLogic.getInstance().getScore());
				scoreToNextLevelText.setText("Score to next Level: "+ GameLogic.getInstance().getScoreToNextLevel());
			}
		});
	}

	private void initializePauseModeButton() {
		pauseButton = new Button();
		ImageView pause = new ImageView(new Image("control/playpause/Pause.png", 50, 50, false, false));
		ImageView play = new ImageView(new Image("control/playpause/Play.png", 50, 50, false, false));
		pauseButton.setGraphic(pause);
		pauseButton.setPrefWidth(150);
		
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
					GameLogic.getInstance().togglePauseMode();
					if (GameLogic.getInstance().isPause()) {
						pauseText.setText("Pause mode: ON");
						pauseButton.setGraphic(play);
						GameLogic.stop();
						if (GameLogic.getInstance().getLevel() == 5)
						try {
							GameLogic.stopFiring();
						}
						catch (Exception e){
							;
						}
					} else {
						pauseText.setText("Pause mode: OFF");
						pauseButton.setGraphic(pause);
						GameLogic.start();
						if (GameLogic.getInstance().getLevel() == 5)
							try {
								GameLogic.startFiring();
							}
							catch (Exception e){
								;
							}
					}
			}
		});
	}

	private void initializeNextLevelButton() {
		nextLevelButton = new Button("Click to go to next level");
		nextLevelButton.setFont(Font.font(15));
		nextLevelButton.setMinWidth(200);
		nextLevelButton.setMaxWidth(200);
		nextLevelButton.setStyle("-fx-font-family: 'Jungle Adventurer'");
		nextLevelButton.setPrefWidth(50);
		nextLevelButton.setVisible(false);
		
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (nextLevelButton.isVisible()) {
					nextLevelButton.setVisible(false);
					GameLogic.getInstance().setLevel(GameLogic.getInstance().getLevel() + 1);
					GameLogic.getInstance().newGame(GameLogic.getInstance().getLevel());
					pauseText.setText("Pause mode : OFF");
					levelText.setText("Level: " + GameLogic.getInstance().getLevel());
					scoreText.setText("Score: " + GameLogic.getInstance().getScore());
					scoreToNextLevelText.setText("Score to next Level : "+GameLogic.getInstance().getScoreToNextLevel());
					GameLogic.getInstance().getGamePane().getSnake().initialize();
					GameLogic.getInstance().getGamePane().getApple().initialize();
				}
			}
		});
	}

	private void initializeLevelText() {
		levelText = new Text("Level : " + 1);
		levelText.setFont(Font.font(30));
		levelText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	private void initializePauseModeText() {
		pauseText = new Text("Pause mode : OFF");
		pauseText.setFont(Font.font(30));
		pauseText.setStyle("-fx-font-family: 'Jungle Adventurer'");	}

	private void initializeScoreText() {
		scoreText = new Text("Score : " + 0);
		scoreText.setFont(Font.font(30));
		scoreText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	private void initializeScoreToNextLevelText() {
		scoreToNextLevelText = new Text("Score to next level : " + 10);
		scoreToNextLevelText.setFont(Font.font(30));
		scoreToNextLevelText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	private void initializeStaminaText() {
		staminaText = new Text("Stamina : " + 100);
		staminaText.setFont(Font.font(30));
		staminaText.setStyle("-fx-font-family: 'Jungle Adventurer'");
		
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
		nextLevelButton.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public Text getLevelText() {
		return levelText;
	}

	public void setLevelText(String text) {
		levelText.setText(text);
		levelText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public Text getPauseText() {
		return pauseText;
	}

	public void setPauseText(Text pauseText) {
		this.pauseText = pauseText;
		pauseText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public Text getScoreText() {
		return scoreText;
	}

	public void setScoreText(Text scoreText) {
		this.scoreText = scoreText;
		scoreText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public Text getScoreToNextLevelText() {
		return scoreToNextLevelText;
	}

	public void setScoreToNextLevelText(Text scoreToNextLevelText) {
		this.scoreToNextLevelText = scoreToNextLevelText;
		scoreToNextLevelText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public void setNextLevelButtonText(String text) {
		nextLevelButton.setText(text);
		nextLevelButton.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public void setScoreText(String text) {
		scoreText.setText(text);
		scoreText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public void setScoreToNextLevelText(String text) {
		scoreText.setText(text);
		scoreText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public void setLevelText(Text levelText) {
		this.levelText = levelText;
		levelText.setStyle("-fx-font-family: 'Jungle Adventurer'");
	}

	public Text getStaminaText() {
		return staminaText;
	}

	public void setStaminaText(int sp) {
		if(sp < 0) {
			staminaText.setText("Stamina = " + 0);
			staminaText.setStyle("-fx-font-family: 'Jungle Adventurer'");
		}
		else {
			staminaText.setText("Stamina = " + sp);
			staminaText.setStyle("-fx-font-family: 'Jungle Adventurer'");
		}
	}
}
