package logic;

import java.io.File;

import base.Stamina;
import gui.ControlPane;
import gui.GamePane;
import item.Apple;
import item.BadApple;
import item.Energy;
import item.SlowPotion;
import item.SpeedPotion;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import monster.Monster1;
import monster.Wall;
import snake.Head;
import snake.Snake;

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
	private int scoreToNextLevel;
	private GamePane gamePane;
	private ControlPane controlPane;
	private static MediaPlayer bgmSound;
	private static MediaPlayer eatingSound;
	private static MediaPlayer collectItemSound;
	
	private static MediaPlayer gameWinSound;
	private static MediaPlayer gameOverSound;
	private static Thread moving;
	private static Thread usingStamina;
//	Media sound = new Media(new File(musicFile).toURI().toString());
//	MediaPlayer mediaPlayer = new MediaPlayer(sound);
//	mediaPlayer.play();

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
		// run only once (run when using gameLogic.getInstance() for the first time)
		String gameWinSoundFile = "gamewin-sound.wav";
		Media gameWinSfx = new Media(getClass().getClassLoader().getResource(gameWinSoundFile).toString());
		gameWinSound = new MediaPlayer(gameWinSfx);
		
		String gameOverSoundFile = "gameover-sound.wav";
		Media gameOverSFx = new Media(getClass().getClassLoader().getResource(gameOverSoundFile).toString());
		gameOverSound = new MediaPlayer(gameOverSFx);
		
		String eatingSoundFile = "eating-sound.wav"; // For example
		Media eatingSfx = new Media(getClass().getClassLoader().getResource(eatingSoundFile).toString());
		eatingSound = new MediaPlayer(eatingSfx);
		
		String collectItemSoundFile = "collect-item-sound.wav";
		Media collectItemSfx = new Media(getClass().getClassLoader().getResource(collectItemSoundFile).toString());
		collectItemSound = new MediaPlayer(collectItemSfx);
		
		String bgmFile = "bgm.mp3"; // For example
		Media bgm = new Media(getClass().getClassLoader().getResource(bgmFile).toString());
		bgmSound = new MediaPlayer(bgm);
		bgmSound.setCycleCount(AudioClip.INDEFINITE);
		bgmSound.play();
		
		
//		String gameWinSoundFile = "gamewin-sound.wav";
//		Media gameWinSfx = new Media(new File(gameWinSoundFile).toURI().toString());
//		gameWinSound = new MediaPlayer(gameWinSfx);
//		
//		String gameOverSoundFile = "gameover-sound.wav";
//		Media gameOverSFx = new Media(new File(gameOverSoundFile).toURI().toString());
//		gameOverSound = new MediaPlayer(gameOverSFx);
//		
//		String eatingSoundFile = "eating-sound.wav"; // For example
//		Media eatingSfx = new Media(new File(eatingSoundFile).toURI().toString());
//		eatingSound = new MediaPlayer(eatingSfx);
//		
//		String collectItemSoundFile = "collect-item-sound.wav";
//		Media collectItemSfx = new Media(new File(collectItemSoundFile).toURI().toString());
//		collectItemSound = new MediaPlayer(collectItemSfx);
//		
//		String bgmFile = "bgm.mp3"; // For example
//		Media bgm = new Media(new File(bgmFile).toURI().toString());
//		bgmSound = new MediaPlayer(bgm);
//		bgmSound.setCycleCount(AudioClip.INDEFINITE);
//		bgmSound.play();
		
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(true);// -----
		this.setBgmOn(true);
		this.setSfxOn(true);
		this.setNumberOfMovingThread(0);
		this.setSleepTime(150);
		this.setScore(0);
		this.setScoreToNextLevel(5);
		this.setLevel(level);
		this.gamePane = new GamePane();
		this.controlPane = new ControlPane(gamePane);
		this.gamePane.getSnake().initializeSnake();
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
		// -----
//		this.start();
		// -----
	}

	public void newGame(int level) {
		// run when press start or newGameButton
		
//		initMovingAndUsingStaminaThread();
//		try {
//			if(GameLogic.getInstance().isBgmOn())
//			{
//				GameLogic.getInstance().getBgmSound().play();
//			}
//		}
//		catch (Exception e){
//			;
//		}
		
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(false);
		this.setSleepTime(150);
		this.setScore(0);
		this.setScoreToNextLevel(5 * level);
		this.setLevel(level);
		this.gamePane.getSnake().initializeSnake();
//		this.gamePane.getApple().initialize();
//		this.gamePane.moveToRandomLocation(gamePane.getApple());
		this.controlPane.getNextLevelButton().setVisible(false);

		for (BadApple b : BadApple.allBadApple) {
			b.setVisible(false);
		}
		BadApple.amount = 0;

		for (SlowPotion m : SlowPotion.allSlowPotion) {
			m.setVisible(false);
		}
		SlowPotion.amount = 0;

		for (Monster1 mo : Monster1.allMonster) {
			mo.setVisible(false);
		}
		Monster1.amount = 0;

		for (SpeedPotion p : SpeedPotion.allSpeedPotion) {
			p.setVisible(false);
		}
		SpeedPotion.amount = 0;

		for (Wall w : Wall.allWall) {
			w.setVisible(false);
		}
		Wall.amount = 0;

		for (Energy e : Energy.allEnergyPotion) {
			e.setVisible(false);
		}
		Energy.amount = 0;

		switch (level) {
		case 1:
			this.setSleepTime(100);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.allBadApple.get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			break;
		case 2:
			this.setSleepTime(100);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.allBadApple.get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			for (int i = 0; i < 3; i++) {
				Energy e = Energy.allEnergyPotion.get(i);
				e.initialize();
				this.gamePane.moveToRandomLocation(e);
			}
			break;
		case 3:
			this.setSleepTime(100);
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.allBadApple.get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.allSlowPotion.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 3; i++) {
				Energy e = Energy.allEnergyPotion.get(i);
				e.initialize();
				this.gamePane.moveToRandomLocation(e);
			}
			break;
		case 4:
			this.setSleepTime(80);
			for (Wall w : Wall.allWall) {
				w.initialize();
			}
			this.getGamePane().updateLocation();
			for (int i = 0; i < 1; i++) {
				BadApple b = BadApple.allBadApple.get(i);
				b.initialize();
				this.gamePane.moveToRandomLocation(b);
			}
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.allSlowPotion.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 2; i++) {
				Monster1 m = Monster1.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 1; i++) {
				SpeedPotion p = SpeedPotion.allSpeedPotion.get(i);
				p.initialize();
				this.gamePane.moveToRandomLocation(p);
			}
			break;
		case 5:
			this.setSleepTime(80);
			for (Wall w : Wall.allWall) {
				w.initialize();
			}
			this.getGamePane().updateLocation();
			for (int i = 0; i < 4; i++) {
				Monster1 m = Monster1.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 4; i++) {
				SpeedPotion p = SpeedPotion.allSpeedPotion.get(i);
				p.initialize();
				this.gamePane.moveToRandomLocation(p);
			}
			break;
		case 6:
			this.setGameEnd(true);
			this.setGameWin(true);
			this.checkGameEnd();
		}

		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
		this.gamePane.updateLocation();
		start();// -------------------------------------------------------------------------
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
//		if(GameLogic.getInstance().isMoveFinished()) {
//			if (score == 1) {
//				this.setGameEnd(true);
//				this.setGameWin(true);
//				GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
//			}
//			checkGameEnd();
//		}
//		if (score == 1) {
//			this.setGameEnd(true);
//			this.setGameWin(true);
//			GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
//		}
//		checkGameEnd();
	}

	public void checkGameEnd() {
		if (GameLogic.getInstance().isMoveFinished()) {
			if (score == 1) {// GameLogic.getInstance().getLevel() * 5
				this.setGameEnd(true);
				this.setGameWin(true);
				GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
			}
		}
		if (isGameEnd) {
//			try {
//				GameLogic.getInstance().getBgmSound().stop();
////				GameLogic.getInstance().toggleBgm();
//			} catch (NullPointerException e) {
//				;
//			}
//			GameLogic.getInstance().getControlPane().getBgmPlayer().stop();
//			GameLogic.getInstance().toggleBgm();
			if (isGameWin) {
				if (GameLogic.getInstance().isSfxOn()) {
					gameWinSound.seek(gameWinSound.getStartTime());
					gameWinSound.play();
				}
//				gameWinSound.seek(gameWinSound.getStartTime());
//				gameWinSound.play();
				controlPane.setScoreText("You win!");
			} else {
				if (GameLogic.getInstance().isSfxOn()) {
					gameOverSound.seek(gameOverSound.getStartTime());
					gameOverSound.play();
//					bgmSound.stop();
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
//		int currentNumberOfMovingThread = GameLogic.getInstance().getNumberOfMovingThread();// cause of java.lang.OutOfMemoryError: Java heap space
	}
	
	public static void stop() {
		
//		int currentNumberOfMovingThread = GameLogic.getInstance().getNumberOfMovingThread();// cause of java.lang.OutOfMemoryError: Java heap space
		
		moving.interrupt();
		usingStamina.interrupt();
	}
	
	public static void moveSnake() throws InterruptedException {
		GameLogic.getInstance().setMoveFinished(false);
		GamePane temp = GameLogic.getInstance().getControlPane().getGamePane();
		temp.getSnake().changeLocation();
		temp.updateLocation();
		temp.checkInteract(temp.getSnake().getHead().getXLocation(), temp.getSnake().getHead().getYLocation());
		if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
//			System.out.println("Crash!");
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
				// TODO Auto-generated method stub
				GameLogic.getInstance().getControlPane().getGamePane().requestFocus();
			}
		});

//				System.out.println("Current Thread ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(GameLogic.getInstance().getSleepTime());
//					Thread.sleep(1000);
//					MovingThread.sleep(200);
		} catch (Exception e) {
//			e.printStackTrace();
		}

	}

	public static void runStamina() throws InterruptedException {
		Stamina snakeStamina = GameLogic.getInstance().getGamePane().getSnake().getStamina();
//		System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
//		System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
		snakeStamina.decrementStamina(2);
//		System.out.println(GameLogic.getInstance().getNumberOfMovingThread());
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GameLogic.getInstance().getControlPane().setStaminaText(snakeStamina.getSp());
//				System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
//				System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
			}
		});
//		snakeStamina.setStop(true);

		if (snakeStamina.isStaminaDepleted()) {

			GameLogic.getInstance().setGameEnd(true);
			GameLogic.getInstance().checkGameEnd();
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					GameLogic.getInstance().setGameEnd(true);
//					GameLogic.getInstance().checkGameEnd();
//				}
//			});
//			endGame();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
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
		this.gameWinSound = gameWinSound;
	}

	public MediaPlayer getGameOverSound() {
		return gameOverSound;
	}

	public void setGameOverSound(MediaPlayer gameOverSound) {
		this.gameOverSound = gameOverSound;
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
//				int currentNumberOfMovingThread = GameLogic.getInstance().getNumberOfMovingThread();
				GameLogic.getInstance().setNumberOfMovingThread(GameLogic.getInstance().getNumberOfMovingThread() + 1);

				while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()
						&& GameLogic.getInstance().getNumberOfMovingThread() <= 1) {
					try {
						moveSnake();
//						runStamina();
//							System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
//							System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
					} catch (InterruptedException e1) {
//						e1.printStackTrace();
					}

				}
				GameLogic.getInstance().setNumberOfMovingThread(GameLogic.getInstance().getNumberOfMovingThread() - 1);
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					// check every 0.1 second if gameEnd still true
			}
		};
		
		usingStamina = new Thread() {
			public void run() {
				GameLogic.getInstance().setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() + 1);

				while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()
						&& GameLogic.getInstance().getNumberOfStaminaThread() <= 1) {
					try {
//						moveSnake();
						runStamina();
//							System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
//							System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				}
				GameLogic.getInstance().setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() - 1);
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					// check every 0.1 second if gameEnd still true
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
	
	
}
