package logic;

import gui.ControlPane;
import item.Food;
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
	private Food food;
	private ControlPane controlPane;

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
		switch (level) {
		case 1:
			this.setGameEnd(false);
			this.setGameWin(false);
			this.setScore(0);
			this.setLevel(1);
			this.setFramerate(400);
			this.setPause(false);
			break;
		case 2:
			this.setGameEnd(false);
			this.setGameWin(false);
			this.setScore(0);
			this.setLevel(2);
			this.setFramerate(400);
			this.setPause(false);
			break;
		case 3:
			this.setGameEnd(false);
			this.setGameWin(false);
			this.setScore(0);
			this.setLevel(3);
			this.setFramerate(400);
			this.setPause(false);
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void updateScore(int score) {
		this.setScore(score);
		checkGameEnd();
		if (isGameEnd) {
			if (isGameWin) {
				controlPane.updateScoreText("You win!");
				return;
			} else {
				controlPane.updateScoreText("You lose!");
				return;

			}
		} else {
			controlPane.updateScoreText("Score :" + this.score);
		}
	}

	public ControlPane getControlPane() {
		return controlPane;
	}

	private void checkGameEnd() {
		if (score == 10) {
			//temporary
			this.setGameEnd(true);
			this.setGameWin(true);
			
			//go to next level
			//code
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
