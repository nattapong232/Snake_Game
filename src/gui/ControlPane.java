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
import snake.Body;
import snake.Tail;
import javafx.scene.media.AudioClip;
//import logic.MovingThread;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

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
	private GamePane gamePane;
//	private MediaPlayer bgmPlayer;
//	private MediaPlayer sfxPlayer;
//	Media sound = new Media(new File(musicFile).toURI().toString());
//	MediaPlayer mediaPlayer = new MediaPlayer(sound);
//	mediaPlayer.play();

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

		hBox.getChildren().addAll(sfxButton, bgmButton);
		this.getChildren().addAll(nextLevelButton, staminaText, levelText, scoreText, scoreToNextLevelText, pauseText,
				hBox, pauseButton, newGameButton);
	}

	private void initializeBgm() {
		bgmButton = new Button();
		ImageView bgmOn = new ImageView(new Image("bgm.png", 30, 30, false, false));
		ImageView bgmOff = new ImageView(new Image("bgmOff.png", 30, 30, false, false));
		bgmButton.setGraphic(bgmOn);
		bgmButton.setPrefWidth(150);
//		String bgmFile = "bgm.mp3"; // For example
//		Media bgm = new Media(new File(bgmFile).toURI().toString());
//		bgmPlayer = new MediaPlayer(bgm);
//		bgmPlayer.setCycleCount(AudioClip.INDEFINITE);
//		bgmPlayer.play();
		bgmButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (!GameLogic.getInstance().isGameEnd()) {
//					System.out.println(GameLogic.getInstance().isBgmOn());
					GameLogic.getInstance().toggleBgm();
					// turn on sound,turn off sound
					if (GameLogic.getInstance().isBgmOn()) {
						GameLogic.getInstance().getBgmSound().play();
						bgmButton.setGraphic(bgmOn);
					} else {
						GameLogic.getInstance().getBgmSound().pause();
						bgmButton.setGraphic(bgmOff);
					}
				}
			}
		});
	}

	private void initializeSfx() {
		sfxButton = new Button();
		ImageView sfxOn = new ImageView(new Image("sfx.png", 30, 30, false, false));
		ImageView sfxOff = new ImageView(new Image("sfxOff.jpg", 30, 30, false, false));
		sfxButton.setGraphic(sfxOn);
		sfxButton.setPrefWidth(150);
//		String eatingSoundFile = "eating-sound.wav"; // For example
//		Media eatingSfx = new Media(new File(eatingSoundFile).toURI().toString());
//		sfxPlayer = new MediaPlayer(eatingSfx);
//		sfxPlayer.play();
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
//				System.out.println(GameLogic.getInstance().getNumberOfMovingThread());
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
				pauseText.setText("Pause mode : OFF");
				levelText.setText("Level : " + GameLogic.getInstance().getLevel());
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
						GameLogic.stop();

					} else {
						pauseText.setText("Pause mode : OFF");
						GameLogic.start();
//						GameLogic.startMoving();
					}
				}
				
//				System.out.println(GameLogic.getInstance().getLevel());
//				for (int i = 0; i < 40; i++) {
//					for (Body b : GameLogic.getInstance().getGamePane().getSnake().getSnake())
//					{
//						b.setVisible(true);
//					}
//			}
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
//						for (Node n : GameLogic.getInstance().getGamePane().getLocationTable()[i][j]) {
//
//							if (n.getClass().equals(Tail.class)) {
//								System.out.println("i = " + i + " j = " + j + "  " + n.getClass() + " " + GameLogic
//										.getInstance().getGamePane().getSnake().getSnake().indexOf((Tail) n));
////								System.out.println(GameLogic.getInstance().getGamePane().getSnake().getSnake().indexOf((Tail) n));
//							} else {
//								System.out.println("i = " + i + " j = " + j + "  " + n.getClass());
//							}
//						}
//					}
//				}
//				System.out.println(GameLogic.getInstance().getNumberOfMovingThread());
//				System.out.println(Mushroom.allMushroom.get(0).getXLocation());
			}
		});
	}

	private void initializeNextLevelButton() {
		nextLevelButton = new Button("Click to go to next level");
		nextLevelButton.setFont(Font.font(25));
		nextLevelButton.setStyle("-fx-font-family: 'serif'");
		nextLevelButton.setVisible(false);
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (nextLevelButton.isVisible()) {
					nextLevelButton.setVisible(false);
					GameLogic.getInstance().setLevel(GameLogic.getInstance().getLevel() + 1);
					GameLogic.getInstance().newGame(GameLogic.getInstance().getLevel());
					pauseText.setText("Pause mode : OFF");
					levelText.setText("Level : " + GameLogic.getInstance().getLevel());
					scoreText.setText("Score : " + GameLogic.getInstance().getScore());
					gamePane.getSnake().initializeSnake();
					gamePane.getApple().initialize();
//					System.out.println(GameLogic.getInstance().isGameEnd());
				}
			}
		});
	}

	private void initializeLevelText() {
		levelText = new Text("Level : " + 1);
		levelText.setFont(Font.font(30));
		levelText.setStyle("-fx-font-family: 'serif'");
	}

	private void initializePauseModeText() {
		pauseText = new Text("Pause mode : OFF");
		pauseText.setFont(Font.font(30));
		pauseText.setStyle("-fx-font-family: 'serif'");	}

	private void initializeScoreText() {
		scoreText = new Text("Score : " + 0);
		scoreText.setFont(Font.font(30));
		scoreText.setStyle("-fx-font-family: 'serif'");
	}

	private void initializeScoreToNextLevelText() {
		scoreToNextLevelText = new Text("Score to next level : " + 10);
		scoreToNextLevelText.setFont(Font.font(30));
		scoreToNextLevelText.setStyle("-fx-font-family: 'serif'");
	}

	private void initializeStaminaText() {
		staminaText = new Text("Stamina : " + 100);
		staminaText.setFont(Font.font(30));
		staminaText.setStyle("-fx-font-family: 'serif'");
		
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
		nextLevelButton.setStyle("-fx-font-family: 'serif'");
	}

	public Text getLevelText() {
		return levelText;
	}

	public void setLevelText(String text) {
		levelText.setText(text);
		levelText.setStyle("-fx-font-family: 'serif'");
	}

	public Text getPauseText() {
		return pauseText;
	}

	public void setPauseText(Text pauseText) {
		this.pauseText = pauseText;
		pauseText.setStyle("-fx-font-family: 'serif'");
	}

	public Text getScoreText() {
		return scoreText;
	}

	public void setScoreText(Text scoreText) {
		this.scoreText = scoreText;
		scoreText.setStyle("-fx-font-family: 'serif'");
	}

	public Text getScoreToNextLevelText() {
		return scoreToNextLevelText;
	}

	public void setScoreToNextLevelText(Text scoreToNextLevelText) {
		this.scoreToNextLevelText = scoreToNextLevelText;
		scoreToNextLevelText.setStyle("-fx-font-family: 'serif'");
	}

	public void setNextLevelButtonText(String text) {
		nextLevelButton.setText(text);
		nextLevelButton.setStyle("-fx-font-family: 'serif'");
	}

	public void setScoreText(String text) {
		scoreText.setText(text);
		scoreText.setStyle("-fx-font-family: 'serif'");
	}

	public void setScoreToNextLevelText(String text) {
		scoreText.setText(text);
		scoreText.setStyle("-fx-font-family: 'serif'");
	}

	public void setLevelText(Text levelText) {
		this.levelText = levelText;
		levelText.setStyle("-fx-font-family: 'serif'");
	}

	public Text getStaminaText() {
		return staminaText;
	}

	public void setStaminaText(int sp) {
		if(sp < 0) {
			staminaText.setText("Stamina = " + 0);
			staminaText.setStyle("-fx-font-family: 'serif'");
		}
		else {
			staminaText.setText("Stamina = " + sp);
			staminaText.setStyle("-fx-font-family: 'serif'");
		}
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}

}
