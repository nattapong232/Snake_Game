package logic;

import gui.ControlPane;

import item.Apple;
import item.Mushroom;
import javafx.application.Platform;
import snake.Head;
import snake.Snake;

public class GameLogic {

	private static GameLogic instance = null;
	private boolean isGameEnd;
	private boolean isGameWin;
	private boolean isPause;
	public int framerate;
	private int score;
	private int level;
	private Snake snake;
	private Apple apple;
	private Mushroom mushroom1;
	private Mushroom mushroom2;
	private Mushroom mushroom3;
	private ControlPane controlPane;

//	private MainMenuPane mainMenuPane;
	// ----------------------------------------------------------
//	private Thread moving = new Thread(() -> {
//			while ((!isGameEnd())) {
//				if (!isPause()) {
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							controlPane.getGamePane().getSnake().requestFocus();
//						}
//					});
//					controlPane.getGamePane().getSnake().move();
//					controlPane.getGamePane().checkEat();
//					if (controlPane.getGamePane().getSnake().isCrash()) {
//						GameLogic.getInstance().setGameEnd(true);
//					}
//				}
//				try {
//					Thread.sleep(framerate);
//				} catch (Exception ex) {
//				}
//		}
//	});
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFramerate() {
		return framerate;
	}

	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	private GameLogic() {
		this.newGame(1);
	}

	public void newGame(int level) {
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setScore(0);
		this.setLevel(level);
		this.setFramerate(300);
		this.setPause(false);
		switch(level) {
		case 2 :
			this.setFramerate(100);
			this.getInstance().getControlPane().getGamePane().getMushroom1().initialize();
			break;
		case 3 :
			this.setFramerate(100);
			this.getInstance().getControlPane().getGamePane().getMushroom1().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom2().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom3().initialize();
			break;
		case 4 :
			this.setFramerate(100);
			this.getInstance().getControlPane().getGamePane().getMushroom1().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom2().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom3().initialize();
			break;
		case 5 :
			this.setFramerate(100);
			this.getInstance().getControlPane().getGamePane().getMushroom1().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom2().initialize();
			this.getInstance().getControlPane().getGamePane().getMushroom3().initialize();
			break;
		}
		
		
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

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Apple getFood() {
		return apple;
	}

	public void setFood(Apple apple) {
		this.apple = apple;
	}

	public void updateScore(int score) {
		this.setScore(score);
		controlPane.updateScoreText("Score :" + this.score);
		checkGameEnd();
		if (score == 3) {
			this.setGameEnd(true);
			this.setGameWin(true);
			GameLogic.getInstance().getControlPane().getNextLevelButton().setVisible(true);
		}
	}

	public ControlPane getControlPane() {
		return controlPane;
	}

	public void checkGameEnd() {
		if (isGameEnd) {
			if (isGameWin) {
				controlPane.updateScoreText("You win!");
			} else {
				controlPane.updateScoreText("You lose!");
				GameLogic.getInstance().setLevel(1);
				GameLogic.getInstance().setScore(0);
			}
		}
	}

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

}
