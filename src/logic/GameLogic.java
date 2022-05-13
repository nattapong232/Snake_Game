package logic;

import gui.ControlPane;
import gui.GamePane;
import gui.Snake;
import item.Apple;
import item.Mushroom;
import item.Poison;
import javafx.application.Platform;
import monster.Monster;
import monster.Wall;
import snake.Head;

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
		//run only once (run when using gameLogic.getInstance() for the first time)
		this.setGameEnd(false);
		this.setGameWin(false);
		this.setPause(false);
		this.setSleepTime(300);
		this.setScore(0);
		this.setLevel(level);
		this.gamePane = new GamePane();
		this.controlPane = new ControlPane(gamePane);
		this.gamePane.getSnake().initializeSnake();
		this.gamePane.getApple().initialize();
		this.gamePane.moveToRandomLocation(gamePane.getApple());
	}

	public void newGame(int level) {
		//run when press start or newGameButton
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
		for (Mushroom m : Mushroom.allMushroom) {
			m.setVisible(false);
		}
		Mushroom.amount = 0;
		for (Monster mo : Monster.allMonster) {
			mo.setVisible(false);
			Monster.amount -= 1;
		}
		Monster.amount = 0;
		for (Poison p : Poison.allPoison) {
			p.setVisible(false);
			Poison.amount -= 1;
		}
		Poison.amount = 0;
		for (Wall w : Wall.allWall) {
			w.setVisible(false);
			Wall.amount -= 1;
		}
		Wall.amount = 0;
		
		switch (level) {
		case 2:
			this.setSleepTime(80);
			for (int i = 0 ; i < 1 ; i++) {
				Mushroom m = Mushroom.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0 ; i < 3 ; i++) {
				Monster m = Monster.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			break;
		case 3:
			this.setSleepTime(80);
			for (int i = 0 ; i < 1 ; i++) {
				Mushroom m = Mushroom.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0 ; i < 5 ; i++) {
				Monster m = Monster.allMonster.get(i);
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
			for (int i = 0 ; i < 1 ; i++) {
				Mushroom m = Mushroom.allMushroom.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0 ; i < 4 ; i++) {
				Monster m = Monster.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0 ; i < 4 ; i++) {
				Poison p = Poison.allPoison.get(i);
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
			for (int i = 0 ; i < 4 ; i++) {
				Monster m = Monster.allMonster.get(i);
				m.initialize();
				this.gamePane.moveToRandomLocation(m);
			}
			for (int i = 0 ; i < 4 ; i++) {
				Poison p = Poison.allPoison.get(i);
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
		if (score == 5) {
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
