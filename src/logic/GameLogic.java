package logic;

import java.util.Random;
import base.MoveableObject;
import base.Stamina;
import etc.Wall;
import food.BadApple;
import gui.ControlPane;
import gui.GamePane;
import item.Battery;
import item.SlowPotion;
import item.SpeedPotion;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import monster.Demon;
import monster.Peashooter;

public class GameLogic {
	private static GameLogic instance = null;
	private boolean isGameEnd;
	private boolean isGameWin;
	private boolean isPause;
	private boolean isBgmOn;
	private boolean isSfxOn;
	private boolean moveFinished; // to prevent gameEnd before snake increase length
	private int sleepTime;
	private int score;
	private int level;
	private int numberOfMovingThread;
	private int numberOfStaminaThread;
	private int numberOfFiringThread;
	private int scoreToNextLevel;
	
	private GamePane gamePane;
	private ControlPane controlPane;

	private static MediaPlayer bgmSound;
	private static MediaPlayer eatingSound;
	private static MediaPlayer collectItemSound;
	private static MediaPlayer gameWinSound;
	private static MediaPlayer gameOverSound;
	private static MediaPlayer shootingSound;
	
	private static Thread moving;
	private static Thread usingStamina;
	private static Thread firing;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	private GameLogic() {
		this.newGame();
	}

	public void newGame() {
		String gameWinSoundFile = "sound/GameWin.mp3";
		Media gameWinSfx = new Media(getClass().getClassLoader().getResource(gameWinSoundFile).toString());
		gameWinSound = new MediaPlayer(gameWinSfx);

		String gameOverSoundFile = "sound/GameOver.mp3";
		Media gameOverSFx = new Media(getClass().getClassLoader().getResource(gameOverSoundFile).toString());
		gameOverSound = new MediaPlayer(gameOverSFx);

		String eatingSoundFile = "sound/Eating.wav";
		Media eatingSfx = new Media(getClass().getClassLoader().getResource(eatingSoundFile).toString());
		eatingSound = new MediaPlayer(eatingSfx);

		String collectingItemSoundFile = "sound/CollectingItem.wav";
		Media collectingItemSfx = new Media(
				getClass().getClassLoader().getResource(collectingItemSoundFile).toString());
		collectItemSound = new MediaPlayer(collectingItemSfx);
		
		String shootingSoundFile = "sound/ShootingSound.mp3";
		Media shootingSfx = new Media(getClass().getClassLoader().getResource(shootingSoundFile).toString());
		shootingSound = new MediaPlayer(shootingSfx);

		String bgmFile = "sound/BGM.mp3";
		Media bgm = new Media(getClass().getClassLoader().getResource(bgmFile).toString());
		bgmSound = new MediaPlayer(bgm);
		bgmSound.setCycleCount(AudioClip.INDEFINITE);
		bgmSound.play();

		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(true);
		this.setBgmOn(true);
		this.setSfxOn(true);
		this.setNumberOfMovingThread(0);
		this.setNumberOfStaminaThread(0);
		this.setNumberOfFiringThread(0);
		this.setSleepTime(300);
		this.setScore(0);
		this.setScoreToNextLevel(4);
		this.setLevel(level);
		this.gamePane = new GamePane();
		this.controlPane = new ControlPane(gamePane);
		this.gamePane.getSnake().initialize();
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
	}

	public void newGame(int level) {
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(false);
		this.setSleepTime(300);
		this.setScore(0);
		this.setLevel(level);
		this.controlPane.getNextLevelButton().setVisible(false);

		for (MoveableObject m : gamePane.getAllMoveableObject()) {
			m.setVisible(false);
		}
		BadApple.setAmount(0);
		SlowPotion.setAmount(0);
		Demon.setAmount(0);
		Peashooter.setAmount(0);
		SpeedPotion.setAmount(0);
		Wall.setAmount(0);
		Battery.setAmount(0);
		
		switch (level) {
		case 1:
			this.setScoreToNextLevel(3);
			this.setSleepTime(120);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.getAllBadApple().get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			break;
		case 2:
			this.setScoreToNextLevel(5);
			this.setSleepTime(110);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.getAllBadApple().get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			for (int i = 0; i < 3; i++) {
				Battery e = Battery.getAllBattery().get(i);
				e.initialize();
				this.gamePane.moveToRandomLocation(e);
			}
			break;
		case 3:
			this.setScoreToNextLevel(5);
			this.setSleepTime(100);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.getAllBadApple().get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.getAllSlowPotion().get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 3; i++) {
				Battery e = Battery.getAllBattery().get(i);
				e.initialize();
				this.gamePane.moveToRandomLocation(e);
			}
			break;
		case 4:
			this.setScoreToNextLevel(5);
			this.setSleepTime(80);
			for (Wall w : Wall.getAllWall()) {
				w.initialize();
			}
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.getAllSlowPotion().get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 1; i++) {
				Demon m = Demon.getAllDemon().get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 1; i++) {
				SpeedPotion p = SpeedPotion.getAllSpeedPotion().get(i);
				p.initialize();
				this.gamePane.moveToRandomLocation(p);
			}
			break;
		case 5:
			this.setScoreToNextLevel(5);
			this.setSleepTime(150);

			for (Peashooter mo2 : Peashooter.getAllPeaShooter()) {
				mo2.initialize();
			}
			startFiring();
			break;
		case 6:
			for (MoveableObject m : gamePane.getAllMoveableObject()) {
				m.setVisible(false);
			}
			BadApple.setAmount(0);
			SlowPotion.setAmount(0);
			Demon.setAmount(0);
			Peashooter.setAmount(0);
			SpeedPotion.setAmount(0);
			Wall.setAmount(0);
			Battery.setAmount(0);
			this.setGameEnd(true);
			this.setGameWin(true);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Congratulations");
			alert.setHeaderText(null);
			alert.setContentText("Play again ?");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
			((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					this.newGame(1);
				}
				if (response == ButtonType.CANCEL) {
					Platform.exit();
				}
			});
		}
		
		this.gamePane.getSnake().initialize();
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());

		start();
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean pauseMode) {
		this.isPause = pauseMode;
	}

	public boolean isBgmOn() {
		return isBgmOn;
	}

	public void setBgmOn(boolean isBgmOn) {
		this.isBgmOn = isBgmOn;
	}

	public boolean isSfxOn() {
		return isSfxOn;
	}

	public void setSfxOn(boolean isSfxOn) {
		this.isSfxOn = isSfxOn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void updateScore(int score) {
		this.setScore(score);
		controlPane.setScoreText("Score : " + this.score);
		checkGameEnd();
	}

	public void checkGameEnd() {
		if (GameLogic.getInstance().isMoveFinished()) {
			if (score == scoreToNextLevel) { // scoreToNextLevel
				this.setGameEnd(true);
				this.setGameWin(true);
				GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
			}
		}
		if (isGameEnd) {
			if (isGameWin) {
				if (GameLogic.getInstance().isSfxOn()) {
					gameWinSound.seek(gameWinSound.getStartTime());
					gameWinSound.play();
				}
				controlPane.setScoreText("You win!");
			} else {
				if (GameLogic.getInstance().isSfxOn()) {
					gameOverSound.seek(gameOverSound.getStartTime());
					gameOverSound.play();
				}
				controlPane.setScoreText("You lose!");
				GameLogic.getInstance().setLevel(1);
				GameLogic.getInstance().setScore(0);
			}
		}
	}

//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
	
	public static void start() {
		initMovingAndUsingStaminaThread();
		moving.start();
		usingStamina.start();
	}

	public static void stop() {
		moving.interrupt();
		usingStamina.interrupt();
	}

	public static void startFiring() {
		initFiring();
		firing.start();
	}
	
	public static void stopFiring() {
		firing.interrupt();
	}
	
	public static void moveSnake() throws InterruptedException {
		GameLogic.getInstance().setMoveFinished(false);
		GamePane temp = GameLogic.getInstance().getGamePane();
		temp.getSnake().changeLocation();
		temp.checkInteract();
		if (GameLogic.getInstance().getGamePane().getSnake().isCrash()) {
			GameLogic.getInstance().setGameEnd(true);
			GameLogic.getInstance().setGameWin(false);
			GameLogic.getInstance().checkGameEnd();
		} else {
			temp.getSnake().move();
			GameLogic.getInstance().setMoveFinished(true);
			GameLogic.getInstance().checkGameEnd();
			temp.getSnake().setCanChangeDirection(true);
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GameLogic.getInstance().getGamePane().requestFocus();
			}
		});

		try {
			Thread.sleep(GameLogic.getInstance().getSleepTime());
		} catch (Exception e) {
		}

	}

	public static void runStamina() throws InterruptedException {
		Stamina snakeStamina = GameLogic.getInstance().getGamePane().getSnake().getStamina();
		snakeStamina.decrementStamina(8);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GameLogic.getInstance().getControlPane().setStaminaText(snakeStamina.getSp());
			}
		});

		if (snakeStamina.isStaminaDepleted()) {
			GameLogic.getInstance().setGameEnd(true);
			GameLogic.getInstance().checkGameEnd();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}

	public static void fire() throws InterruptedException {
		System.out.println("Fire!");
		for (Peashooter p : Peashooter.getAllPeaShooter()) {
			int currentXLocation = p.getBullet().getXLocation();
			int currentYLocation = p.getBullet().getYLocation();
			if (p.getBullet().getXLocation() <= 0) {
				stopFiring();
				startFiring();
			}
			else {
				p.getBullet().setLocation(currentXLocation - 30, currentYLocation);
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					p.getBullet().move();
				}
			});
			GameLogic.getInstance().getGamePane().checkHit();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------	

	public void togglePauseMode() {
		this.isPause = !this.isPause;
	}

	public void toggleBgm() {
		this.isBgmOn = !this.isBgmOn;
	}

	public void toggleSfx() {
		this.isSfxOn = !this.isSfxOn;
	}

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		isGameEnd = gameEnd;
	}

	public boolean isGameWin() {
		return isGameWin;
	}

	public void setGameWin(boolean isGameWin) {
		this.isGameWin = isGameWin;
	}

	public void setControlPane(ControlPane controlPane) {
		this.controlPane = controlPane;
	}

	public ControlPane getControlPane() {
		return controlPane;
	}

	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public int getScoreToNextLevel() {
		return scoreToNextLevel;
	}

	public void setScoreToNextLevel(int scoreToNextLevel) {
		this.scoreToNextLevel = scoreToNextLevel;
	}

	public boolean isMoveFinished() {
		return moveFinished;
	}

	public void setMoveFinished(boolean moveFinished) {
		this.moveFinished = moveFinished;
	}

	public MediaPlayer getGameWinSound() {
		return gameWinSound;
	}

	public void setGameWinSound(MediaPlayer gameWinSound) {
		GameLogic.gameWinSound = gameWinSound;
	}

	public MediaPlayer getGameOverSound() {
		return gameOverSound;
	}

	public void setGameOverSound(MediaPlayer gameOverSound) {
		GameLogic.gameOverSound = gameOverSound;
	}

	public int getNumberOfMovingThread() {
		return numberOfMovingThread;
	}

	public void setNumberOfMovingThread(int numberOfMovingThread) {
		this.numberOfMovingThread = numberOfMovingThread;
	}

	public int getNumberOfStaminaThread() {
		return numberOfStaminaThread;
	}

	public void setNumberOfStaminaThread(int numberOfStaminaThread) {
		this.numberOfStaminaThread = numberOfStaminaThread;
	}

	private static void initMovingAndUsingStaminaThread() {
		moving = new Thread() {
			public void run() {
				GameLogic.getInstance().setNumberOfMovingThread(GameLogic.getInstance().getNumberOfMovingThread() + 1);

				while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()
						&& GameLogic.getInstance().getNumberOfMovingThread() <= 1) {
					try {
						moveSnake();
					} catch (InterruptedException e1) {
					}

				}
				GameLogic.getInstance().setNumberOfMovingThread(GameLogic.getInstance().getNumberOfMovingThread() - 1);
			}
		};

		usingStamina = new Thread() {
			public void run() {
				GameLogic.getInstance().setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() + 1);
				while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()
						&& GameLogic.getInstance().getNumberOfStaminaThread() <= 1) {
					try {
						runStamina();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				GameLogic.getInstance().setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() - 1);
			}
		};
	}

	public static void initFiring() {
		firing = new Thread() {
			public void run() {
				for (Peashooter p : Peashooter.getAllPeaShooter()) {
					if(p.getBullet().getXLocation() <= 0)
						p.getBullet().setLocation(p.getXLocation()-27, p.getYLocation()+3);
				}
				if (GameLogic.getInstance().isSfxOn()) {
					shootingSound.seek(shootingSound.getStartTime());
					shootingSound.play();
				}
				GameLogic.getInstance().setNumberOfFiringThread(GameLogic.getInstance().getNumberOfFiringThread() + 1);
				System.out.println("initFiring");
				while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()
						&& GameLogic.getInstance().getNumberOfFiringThread() <= 1) {
					try {
						fire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				GameLogic.getInstance().setNumberOfFiringThread(GameLogic.getInstance().getNumberOfFiringThread() - 1);
			}
		};
	}

	public static MediaPlayer getBgmSound() {
		return bgmSound;
	}

	public static void setBgmSound(MediaPlayer bgmSound) {
		GameLogic.bgmSound = bgmSound;
	}

	public static MediaPlayer getEatingSound() {
		return eatingSound;
	}

	public static void setEatingSound(MediaPlayer eatingSound) {
		GameLogic.eatingSound = eatingSound;
	}

	public static MediaPlayer getCollectItemSound() {
		return collectItemSound;
	}

	public static void setCollectItemSound(MediaPlayer collectItemSound) {
		GameLogic.collectItemSound = collectItemSound;
	}

	public int getNumberOfFiringThread() {
		return numberOfFiringThread;
	}

	public void setNumberOfFiringThread(int numberOfFiringThread) {
		this.numberOfFiringThread = numberOfFiringThread;
	}

	public static MediaPlayer getShootingSound() {
		return shootingSound;
	}

	public static void setShootingSound(MediaPlayer shootingSound) {
		GameLogic.shootingSound = shootingSound;
	}
}