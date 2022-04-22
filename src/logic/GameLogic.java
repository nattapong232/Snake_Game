package logic;

import Item.Food;
import gui.ControlPane;
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
	private Snake snake;
	private Food food;
	private ControlPane controlPane;

	// ----------------------------------------------------------
	private Thread moving = new Thread(() -> {
		while (true) {
			while ((!isGameEnd())) {
				if (!isPause()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							controlPane.getGamePane().getSnake().requestFocus();
						}
					});
					controlPane.getGamePane().getSnake().move();
					controlPane.getGamePane().checkEat();
					if (controlPane.getGamePane().getSnake().isCrash()) {
						GameLogic.getInstance().setGameEnd(true);
					}
				}
				try {
					Thread.sleep(framerate);
				} catch (Exception ex) {
				}
			}
		}
	});

	public int getFramerate() {
		return framerate;
	}

	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	public Thread getMoving() {
		return moving;
	}

	public void setMoving(Thread moving) {
		this.moving = moving;
	}

	private GameLogic() {
		this.newGame();
	}

	public void newGame() {
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setScore(0);
		this.setFramerate(200);
		this.setPause(false);

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
			this.setGameEnd(true);
			this.setGameWin(true);
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
