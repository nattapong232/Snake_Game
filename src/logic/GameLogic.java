package logic;

import java.io.File;
import java.util.Random;

import base.MoveableObject;
import base.Stamina;
import etc.Wall;
import food.Apple;
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
import javafx.scene.text.Text;
import monster.Demon;
import monster.Peashooter;
import snake.Body;
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
		this.setPause(true);// -----
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
		// -----
//		this.start();
		// -----
	}

	public void newGame(int level) {

		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(false);
		this.setSleepTime(300);
		this.setScore(0);
//		this.setScoreToNextLevel(3 * level);
		this.setLevel(level);
//		this.gamePane.getSnake().initialize();
//		this.gamePane.getApple().initialize();
//		this.gamePane.moveToRandomLocation(gamePane.getApple());
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
		
//		for (BadApple b : BadApple.getAllBadApple()) {
//			b.setVisible(false);
//		}
//		BadApple.setAmount(0);
//
//		for (SlowPotion m : SlowPotion.getAllSlowPotion()) {
//			m.setVisible(false);
//		}
//		SlowPotion.setAmount(0);
//
//		for (Demon mo : Demon.getAllDemon()) {
//			mo.setVisible(false);
//		}
//		Demon.setAmount(0);
//
//		for (Monster2 mo : Monster2.getAllMonster2()) {
//			mo.setVisible(false);
//		}
//		Monster2.setAmount(0);
//		
//		for (SpeedPotion p : SpeedPotion.getAllSpeedPotion()) {
//			p.setVisible(false);
//		}
//		SpeedPotion.setAmount(0);
//
//		for (Wall w : Wall.getAllWall()) {
//			w.setVisible(false);
//		}
//		Wall.setAmount(0);
//
//		for (Battery e : Battery.getAllEnergyPotion()) {
//			e.setVisible(false);
//		}
//		Battery.setAmount(0);

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
			this.setScoreToNextLevel(1);
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
//			for (int i = 0; i < 1; i++) {
//				BadApple b = BadApple.getAllBadApple().get(i);
//				b.initialize();
//				this.gamePane.moveToRandomLocation(b);
//			}
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
//			for (Wall w : Wall.getAllWall()) {
//				w.initialize();
//			}
			for (Peashooter mo2 : Peashooter.getAllPeaShooter()) {
				mo2.initialize();
			}
//			GameLogic.stop();
			startFiring();

//			for (int i = 0; i < 1; i++) {
//				Demon m = Demon.getAllMonster().get(i);
//				m.initialize();
//				this.gamePane.moveToRandomLocation(m);
//			}
//			for (int i = 0; i < 2; i++) {
//				SpeedPotion p = SpeedPotion.getAllSpeedPotion().get(i);
//				p.initialize();
//				this.gamePane.moveToRandomLocation(p);
//			}

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
//			this.checkGameEnd();
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Congratulations");
			a.setHeaderText(null);
			a.setContentText("Play again ?");
			((Button) a.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
			((Button) a.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
			a.showAndWait().ifPresent(response -> {
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
			if (score == scoreToNextLevel) {// scoreToNextLevel
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
//		temp.getSnake().move();
		temp.checkInteract();
		if (GameLogic.getInstance().getGamePane().getSnake().isCrash()) {
//			System.out.println("Crash!");
			GameLogic.getInstance().setGameEnd(true);
			GameLogic.getInstance().setGameWin(false);
			GameLogic.getInstance().checkGameEnd();
//			System.out.println("--------");
//			System.out.println(GameLogic.getInstance().getGamePane().getSnake().getSnake().size());
//			int i = 0;
//			for (Body b : GameLogic.getInstance().getGamePane().getSnake().getSnake()) {
//				System.out.println(i+" "+b.getDirection());
//			}
		} else {
			temp.getSnake().move();
//			temp.checkEat();
//			System.out.println(temp.getSnake().getHead().getBoundsInParent().intersects(temp.getApple().getBoundsInParent()));
			GameLogic.getInstance().setMoveFinished(true);
			GameLogic.getInstance().checkGameEnd();
			temp.getSnake().setCanChangeDirection(true);
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameLogic.getInstance().getGamePane().requestFocus();
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
		snakeStamina.decrementStamina(8);
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

		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	public static void fire() throws InterruptedException {
		System.out.println("Fire!");
		for (Peashooter p : Peashooter.getAllPeaShooter()) {
			int currentXLocation = p.getBullet().getXLocation();
			int currentYLocation = p.getBullet().getYLocation();
//			System.out.println("x = " + currentXLocation + " y = " + currentYLocation);
//			mo2.getBullet().setLocation(currentXLocation - 30, currentYLocation);
			if (p.getBullet().getXLocation() <= 0) {
//				firing.interrupt();
				stopFiring();
				startFiring();
//				p.getBullet().setLocation(p.getXLocation()-27, currentYLocation);
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
			Random rand = new Random();
//			try {
//				Thread.sleep(rand.nextInt(10) * 5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
////						e.printStackTrace();
//			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//					e.printStackTrace();
		}
//				System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
//				System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());

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
				GameLogic.getInstance()
						.setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() + 1);

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
				GameLogic.getInstance()
						.setNumberOfStaminaThread(GameLogic.getInstance().getNumberOfStaminaThread() - 1);
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					// check every 0.1 second if gameEnd still true
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
//						moveSnake();
						fire();
//							System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
//							System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
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
