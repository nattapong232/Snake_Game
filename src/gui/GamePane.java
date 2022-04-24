package gui;

import java.util.ArrayList;
import java.util.Random;

import base.Body;
import base.Coordinate;
import interfaces.Eatable;
import interfaces.Moveable;
import item.Apple;
import item.Food;
import item.Mushroom;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import logic.GameLogic;
import snake.Head;
import snake.Snake;
import javafx.scene.input.KeyEvent;

public class GamePane extends Pane {
	private Snake snake;
	private Apple apple;
	private Mushroom mushroom1;
	private Mushroom mushroom2;
	private Mushroom mushroom3;
	private ArrayList<Node>[][] locationTable;

	public GamePane(int x, int y) {
		snake = new Snake(x, y);
		apple = new Apple();
		mushroom1 = new Mushroom();
		mushroom2 = new Mushroom();
		mushroom3 = new Mushroom();
		locationTable = (ArrayList<Node>[][]) new ArrayList[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				locationTable[i][j] = new ArrayList<Node>() ;
			}
		}
		moveToRandomLocation(apple);
		moveToRandomLocation(mushroom1);
		moveToRandomLocation(mushroom2);
		moveToRandomLocation(mushroom3);
		this.getChildren().add(snake);
		this.getChildren().add(apple);
		this.getChildren().add(mushroom1);
		this.getChildren().add(mushroom2);
		this.getChildren().add(mushroom3);
		this.setMinHeight(600);
		this.setMinWidth(600);
		Image image = new Image("background1.jpg", 600, 600, false, false);
		BackgroundSize bgSize = new BackgroundSize(600, 600, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		this.setBackground(new Background(bgImg));

		this.setOnKeyPressed(event -> {

			KeyCode k = event.getCode();
			// -----------------------------------
			// ------------input sound here------------
			// -----------------------------------
			int currentDirection = this.snake.getSnake().get(0).getDirection();
			switch (k) {
			case A:
				if ((currentDirection == 1 || currentDirection == 3) && (!GameLogic.getInstance().isPause())
						&& (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(90);
					snake.getSnake().get(0).setDirection(0);
					currentDirection = 0;
//					snake.changeLocation();
//					updateLocation();
//					checkInteract();
//					if (snake.isCrash()) {
//						GameLogic.getInstance().setGameEnd(true);
//						GameLogic.getInstance().setGameWin(false);
//					}
//					else {
//						snake.move();
//					}
				}
				break;
			case W:
				if ((currentDirection == 0 || currentDirection == 2) && (!GameLogic.getInstance().isPause())
						&& (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(180);
					snake.getSnake().get(0).setDirection(1);
					currentDirection = 1;
				}
				break;
			case D:
				if ((currentDirection == 1 || currentDirection == 3) && (!GameLogic.getInstance().isPause())
						&& (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(270);
					snake.getSnake().get(0).setDirection(2);
					currentDirection = 2;
				}
				break;
			case S:
				if ((currentDirection == 0 || currentDirection == 2) && (!GameLogic.getInstance().isPause())
						&& (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(0);
					snake.getSnake().get(0).setDirection(3);
					currentDirection = 3;
				}
				break;
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

	public Mushroom getMushroom1() {
		return mushroom1;
	}

	public void setMushroom1(Mushroom mushroom1) {
		this.mushroom1 = mushroom1;
	}

	public Mushroom getMushroom2() {
		return mushroom2;
	}

	public void setMushroom2(Mushroom mushroom2) {
		this.mushroom2 = mushroom2;
	}

	public Mushroom getMushroom3() {
		return mushroom3;
	}

	public void setMushroom3(Mushroom mushroom3) {
		this.mushroom3 = mushroom3;
	}

	public void updateLocation() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				locationTable[i][j].clear();
			}
		}
		for (int i = 0; i < snake.getLength(); i++) {
			Body tempBody = snake.getSnake().get(i);
			locationTable[tempBody.getXLocation()/30][tempBody.getYLocation()/30].add(tempBody);
		}
		locationTable[apple.getXLocation()/30][apple.getYLocation()/30].add(apple);
		for (int i = 0; i < Mushroom.amount; i++) {
			Mushroom tempMushroom = Mushroom.allMushroom.get(i);
			locationTable[tempMushroom.getXLocation()/30][tempMushroom.getYLocation()/30].add(tempMushroom);
			locationTable[tempMushroom.getXLocation()/30+1][tempMushroom.getYLocation()/30].add(tempMushroom);
			locationTable[tempMushroom.getXLocation()/30][tempMushroom.getYLocation()/30+1].add(tempMushroom);
			locationTable[tempMushroom.getXLocation()/30+1][tempMushroom.getYLocation()/30+1].add(tempMushroom);
		}

	}

	public void checkInteract() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (locationTable[i][j] != null) {
					ArrayList<Node> temp = locationTable[i][j];
					if ((temp.size() > 1) && (temp.contains(snake.getHead()))) {
						if ((temp.get(1) instanceof Eatable)) {
							if((temp.get(1).isVisible()))
							{
								checkEat(i,j);
							}
						} else {
							snake.setCrash(true);
						}
					}
				}
			}
		}
	}

	// check what is eaten
	public void checkEat(int x,int y) {
		Node n = locationTable[x][y].get(1);
		if (n instanceof Apple) {
			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() + 1);
			snake.getSnake().get((snake.getLength())).setVisible(true);
			snake.updateLength();
			snake.setLength(snake.getLength());
			moveToRandomLocation((Moveable) n);
		}
		else if (n instanceof Mushroom)
		{		
//			Slow down
			GameLogic.getInstance().setFramerate(200);
			n.setVisible(false);
//			Deduct point, increase snake length
//			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 2);
//			for (int i = 0; i < 3; i++) {
//				snake.getSnake().get((snake.getLength())).setVisible(true);
//				snake.updateLength();
//			}
//			moveToRandomLocation((Moveable) n);
		}
		updateLocation();
	}



	public void moveToRandomLocation(Moveable m) {
		boolean isChanged = false;
		int currentX = m.getXLocation();
		int currentY = m.getYLocation();
		while (!isChanged) {
			m.randomLocation();
			int newX = m.getXLocation();
			int newY = m.getYLocation();
			if(locationTable[newX/30][newY/30].isEmpty()) {
				isChanged = true;
			}
		}
		updateLocation();
//		m.randomLocation();
		m.move();
	}
}
