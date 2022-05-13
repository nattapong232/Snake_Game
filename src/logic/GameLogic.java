package logic;

import gui.ControlPane;
import gui.GamePane;
import item.Apple;
import item.Energy;
import item.SlowPotion;
import item.SpeedPotion;
import javafx.application.Platform;
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
	public int sleepTime;
	private int score;
	private int level;
	private GamePane gamePane;
	private ControlPane controlPane;

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
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(true);//-----
		this.setSleepTime(150);
		this.setScore(0);
		this.setLevel(level);
		this.gamePane = new GamePane();
		this.controlPane = new ControlPane(gamePane);
		this.gamePane.getSnake().initializeSnake();
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
		// -----
		this.start();
		// -----
	}

	public void newGame(int level) {
		// run when press start or newGameButton
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(false);
		this.setSleepTime(300);
		this.setScore(0);
		this.setLevel(level);
		this.gamePane.getSnake().initializeSnake();
//		this.gamePane.getApple().initialize();
//		this.gamePane.moveToRandomLocation(gamePane.getApple());
		this.controlPane.getNextLevelButton().setVisible(false);
		for (SlowPotion m : SlowPotion.allMushroom) {
			m.setVisible(false);
		}
		SlowPotion.amount = 0;
		for (Monster1 mo : Monster1.allMonster) {
			mo.setVisible(false);
			Monster1.amount -= 1;
		}
		Monster1.amount = 0;
		for (SpeedPotion p : SpeedPotion.allPoison) {
			p.setVisible(false);
			SpeedPotion.amount -= 1;
		}
		SpeedPotion.amount = 0;
		for (Wall w : Wall.allWall) {
			w.setVisible(false);
			Wall.amount -= 1;
		}
		Wall.amount = 0;
		
		for (Energy e : Energy.allEnergyPotion) {
			e.setVisible(false);
		}
		Energy.amount = 0;
		
		switch (level) {
		case 2:
			this.setSleepTime(80);
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 3; i++) {
				Energy e = Energy.allEnergyPotion.get(i);
				e.initialize();
				this.gamePane.moveToRandomLocation(e);
			}
			break;
		case 3:
			this.setSleepTime(80);
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 5; i++) {
				Monster1 m = Monster1.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			break;
		case 4:
			this.setSleepTime(80);
			for (Wall w : Wall.allWall) {
				w.initialize();
			}
			this.getGamePane().updateLocation();
			for (int i = 0; i < 1; i++) {
				SlowPotion m = SlowPotion.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 4; i++) {
				Monster1 m = Monster1.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0; i < 4; i++) {
				SpeedPotion p = SpeedPotion.allPoison.get(i);
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
				SpeedPotion p = SpeedPotion.allPoison.get(i);
				p.initialize();
				this.gamePane.moveToRandomLocation(p);
			}
			break;
		}
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
		this.gamePane.updateLocation();
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
		if (score == 1) {
			this.setGameEnd(true);
			this.setGameWin(true);
			GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
		}
	}

	public void checkGameEnd() {
		if (isGameEnd) {
			if (isGameWin) {
				controlPane.setScoreText("You win!");
			} else {
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
//		GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(100);
		Thread moving = new Thread() {
			public void run() {
				while (true) {
					while ((!GameLogic.getInstance().isGameEnd()) && !GameLogic.getInstance().isPause()) {
						try {
							moveSnake();
							runStamina();
							System.out.println(GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp());
							System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// check every 0.1 second if gameEnd still true
				}
			}
		};
		moving.start();
	}

	public static void moveSnake() throws InterruptedException {
		GamePane temp = GameLogic.getInstance().getControlPane().getGamePane();
		temp.getSnake().changeLocation();
		temp.updateLocation();
		temp.checkInteract(temp.getSnake().getHead().getXLocation(), temp.getSnake().getHead().getYLocation());
		if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
			System.out.println("Crash!");
			GameLogic.getInstance().setGameEnd(true);
			GameLogic.getInstance().setGameWin(false);
			GameLogic.getInstance().checkGameEnd();
		} else {
			temp.getSnake().move();
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
			e.printStackTrace();
		}

	}
//	}
//			Thread.sleep(20);
//
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					timerPane[pl].setTimer(plTimer);
//
//					plTimer.decrementTimer(2);
//				}
//			});
////			timerPane[pl].setTimer(plTimer);			
////			
////			plTimer.decrementTimer(2);
//
//		plTimer.setStop(true);
//
//		if (plTimer.isTimerEmpty()) {
//
//			GameLogic.getInstance().setGameEnd(true);
//			GameLogic.getInstance().checkGameEnd();
////			Platform.runLater(new Runnable() {
////				@Override
////				public void run() {
////					GameLogic.getInstance().setGameEnd(true);
////					GameLogic.getInstance().checkGameEnd();
////				}
////			});
////			endGame();
//		}
//	}

	public static void runStamina() throws InterruptedException {
		Stamina snakeStamina = GameLogic.getInstance().getGamePane().getSnake().getStamina();
//		Thread.sleep(20);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GameLogic.getInstance().getControlPane().setStaminaText(new Text("Stamina = " + snakeStamina.getSp()));
				System.out.println(GameLogic.getInstance().getControlPane().getStaminaText());
				snakeStamina.decrementStamina(2);
			}
		});
//			timerPane[pl].setTimer(plTimer);			
//			
//			plTimer.decrementTimer(2);

		snakeStamina.setStop(true);

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
	}

//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------	

	public void togglePauseMode() {
		this.isPause = !this.isPause;
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
}
