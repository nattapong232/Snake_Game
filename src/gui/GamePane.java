package gui;

import java.util.ArrayList;
import base.MoveableObject;
import etc.Wall;
import food.Apple;
import food.BadApple;
import food.Food;
import interfaces.Eatable;
import item.Battery;
import item.Item;
import item.SlowPotion;
import item.SpeedPotion;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import logic.GameLogic;
import monster.Demon;
import monster.Peashooter;
import snake.Body;
import snake.Snake;
import snake.Tail;

public class GamePane extends Pane {
	private Snake snake;
	private Apple apple;
	private ArrayList<MoveableObject> allMoveableObject;

	public GamePane() {

		snake = new Snake(60, 60);
		apple = new Apple();
		allMoveableObject = new ArrayList<MoveableObject>();

		for (int i = 1; i < 15; i++) {
			Tail t = (Tail) snake.getSnake().get(i);
			allMoveableObject.add(t);
		}

		// remain 1.add tail to allMoveableObject (finished) 2. moveToRandomLocation 3. reduce size of snake or food
		allMoveableObject.add(apple);
		for (int i = 0; i < 3; i++) {
			BadApple b = new BadApple();
			allMoveableObject.add(b);
			this.getChildren().add(b);
		}
		
		for (int i = 0; i < 2; i++) {
			Demon d = new Demon();
			allMoveableObject.add(d);
			this.getChildren().add(d);
		}
		
		for (int i = 0; i < 2; i++) {
			SpeedPotion s = new SpeedPotion();
			allMoveableObject.add(s);
			this.getChildren().add(s);
		}
		
		for (int i = 0; i < 2; i++) {
			SlowPotion sl = new SlowPotion();
			allMoveableObject.add(sl);
			this.getChildren().add(sl);
		}
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if ((((i == 0) || (i == 18)) && (j % 3 == 0)) || (((j == 0) || (j == 18)) && (i % 3== 0))) {
					Wall w = new Wall();
					w.setLocation(i * 30, j * 30);
					w.move();
					allMoveableObject.add(w);
					this.getChildren().add(w);
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			Peashooter m = new Peashooter();
			m.setLocation(540, (i + 1) * 120 - 90);
			m.move();
			allMoveableObject.add(m);
			allMoveableObject.add(m.getBullet());
			this.getChildren().add(m);
			this.getChildren().add(m.getBullet());
		}

		for (int i = 0; i < 5; i++) {
			Battery e = new Battery();
			allMoveableObject.add(e);
			this.getChildren().add(e);
		}

		this.getChildren().add(snake);
		this.getChildren().add(apple);
		this.setPrefHeight(600);
		this.setPrefWidth(600);
		this.setMinHeight(600);
		this.setMinWidth(600);
		
		Image image = new Image("background/LevelBG.png", 600, 600, false, false);
		BackgroundSize bgSize = new BackgroundSize(600, 600, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		this.setBackground(new Background(bgImg));

		this.setOnKeyPressed(event -> {
			KeyCode k = event.getCode();
			int currentDirection = this.snake.getSnake().get(0).getDirection();
			if ((!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd())
					&& (snake.isCanChangeDirection())) {
				switch (k) {
				case A:
					if (currentDirection == 1 || currentDirection == 3) {
						snake.getSnake().get(0).setRotate(90);
						snake.getSnake().get(0).setDirection(0);
						currentDirection = 0;
					}
					break;
				case W:
					if (currentDirection == 0 || currentDirection == 2) {
						snake.getSnake().get(0).setRotate(180);
						snake.getSnake().get(0).setDirection(1);
						currentDirection = 1;
					}
					break;
				case D:
					if (currentDirection == 1 || currentDirection == 3) {
						snake.getSnake().get(0).setRotate(270);
						snake.getSnake().get(0).setDirection(2);
						currentDirection = 2;
					}
					break;
				case S:
					if (currentDirection == 0 || currentDirection == 2) {
						snake.getSnake().get(0).setRotate(0);
						snake.getSnake().get(0).setDirection(3);
						currentDirection = 3;
					}
					break;
				}
				snake.setCanChangeDirection(false);
			}
		});
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Apple getApple() {
		return apple;
	}

	public void setApple(Apple apple) {
		this.apple = apple;
	}

	public void checkInteract() {
		// check if snake's head interact with something
		int x = snake.getHead().getXLocation();
		int y = snake.getHead().getYLocation();
		
		for (MoveableObject m : allMoveableObject) {
			int width = (int) m.getFitWidth();
			int height = (int) m.getFitHeight();
			if (m.isVisible()) {
				if ( ((x >= m.getXLocation() ) &&(x <= m.getXLocation() + width - 30))
						&& ((y >= m.getYLocation() ) &&(y <= m.getYLocation() + height - 30))) {
					if (m instanceof Eatable) {
						checkEat(m);
					} else {
						snake.setCrash(true);
					}
				}
			}
		}
	}

	// check what is eaten
	public void checkEat(MoveableObject m) {
		MediaPlayer eatingSound = GameLogic.getInstance().getEatingSound();
		MediaPlayer collectItemSound = GameLogic.getInstance().getCollectItemSound();

		if (m instanceof Food) {
			if (GameLogic.getInstance().isSfxOn()) {
				eatingSound.seek(eatingSound.getStartTime());
				eatingSound.play();
			}
			if (m instanceof Apple) {
				GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() + 1);
				if (GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp() < 100) {
					GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(100);
				}
				snake.getSnake().get((snake.getLength())).initialize();
				snake.updateLength();
				snake.setLength(snake.getLength());
				moveToRandomLocation(m);
//				System.out.println("Eat Apple");
			} else if (m instanceof BadApple) {
				GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 1);
				snake.getSnake().get((snake.getLength())).initialize();
				snake.updateLength();
				snake.setLength(snake.getLength());
				m.setVisible(false);
//				System.out.println("Eat Bad Apple");
			}

		} else if (m instanceof Item) {
			if (GameLogic.getInstance().isSfxOn()) {
				collectItemSound.seek(collectItemSound.getStartTime());
				collectItemSound.play();
			}
			if (m instanceof SlowPotion) {
				GameLogic.getInstance().setSleepTime(140);
				m.setVisible(false);
			} else if (m instanceof SpeedPotion) {
				GameLogic.getInstance().setSleepTime(50);
				m.setVisible(false);
			} else if (m instanceof Battery) {
				int currentSp = GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp();
				snake.getStamina().setSp(currentSp + 30);
				m.setVisible(false);
			}
		}
	}

	public void checkHit() {
		for (Peashooter mo2: Peashooter.getAllPeaShooter()) {
			for (Body b : snake.getSnake()) {
				if (mo2.getBullet().getBoundsInParent().intersects(b.getBoundsInParent()) && b.isVisible() && mo2.getBullet().isVisible()) {
					System.out.println("Hit!");
					mo2.getBullet().setVisible(false);
					GameLogic.getInstance().setGameEnd(true);
					GameLogic.getInstance().setGameWin(false);
					GameLogic.getInstance().checkGameEnd();
				}
			}
		}
	}
	public void moveToRandomLocation(MoveableObject mo) {
		MoveableObject tempObject = new MoveableObject() {
			public void initialize() {
				this.setVisible(false);
			}
		};
		tempObject.setFitWidth(mo.getFitWidth());
		tempObject.setFitHeight(mo.getFitHeight());
		tempObject.setTranslateX(mo.getTranslateX());
		tempObject.setTranslateX(mo.getTranslateY());
		boolean canChange = false;
		mo.setVisible(false);
		mo.randomLocation();
		mo.move();

		while (!canChange) {
			canChange = true;
			for (MoveableObject m : allMoveableObject) {
				if (!m.equals(mo)) {
					if (mo.getBoundsInParent().intersects(m.getBoundsInParent())) {
						canChange = false;
						break;
					}
				}
				if (mo.getBoundsInParent().intersects(tempObject.getBoundsInParent())) {
					canChange = false;
				}
				if (mo.getBoundsInParent().intersects(snake.getHead().getBoundsInParent())) {
					canChange = false;
				}
				if (!canChange) {
					break;
				}
			}
			
			if (!canChange) {
				tempObject.setTranslateX(mo.getTranslateX());
				tempObject.setTranslateX(mo.getTranslateY());
				mo.randomLocation();
				mo.move();
			}
		}
		mo.setVisible(true);
	}

	public ArrayList<MoveableObject> getAllMoveableObject() {
		return allMoveableObject;
	}

	public void setAllMoveableObject(ArrayList<MoveableObject> allMoveableObject) {
		this.allMoveableObject = allMoveableObject;
	}
}