package gui;
import java.util.Random;

import Item.Food;
import interfaces.Locatable;
import interfaces.Moveable;
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
	private Food food;

	public GamePane(int x,int y) {
		snake = new Snake(x,y);
		food = new Food();
		this.getChildren().add(snake);
		this.getChildren().add(food);
		this.setMinHeight(600);
		this.setMinWidth(600);
		Image image = new Image("background1.jpg", 600, 600, false, false);
		BackgroundSize bgSize = new BackgroundSize(600, 600, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		this.setBackground(new Background(bgImg));
		
		this.setOnKeyPressed(event -> {
			
			KeyCode k = event.getCode();
			//-----------------------------------
			//------------input sound here------------
			//-----------------------------------
			int currentDirection = this.snake.getSnake().get(0).getDirection();
			switch (k) {
			case A:
				if ((currentDirection == 1 || currentDirection == 3) && (!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(90);
					snake.getSnake().get(0).setDirection(0);
					currentDirection = 0;
					snake.move();
					checkEat();
				}
				break;
			case W:
				if ((currentDirection == 0 || currentDirection == 2) && (!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(180);
					snake.getSnake().get(0).setDirection(1);
					currentDirection = 1;
					snake.move();
					checkEat();
				}
				break;
			case D:
				if ((currentDirection == 1 || currentDirection == 3) && (!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(270);
					snake.getSnake().get(0).setDirection(2);
					currentDirection = 2;
					snake.move();
					checkEat();
				}
				break;
			case S:
				if ((currentDirection == 0 || currentDirection == 2) && (!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd())) {
					snake.getSnake().get(0).setRotate(0);
					snake.getSnake().get(0).setDirection(3);
					currentDirection = 3;
					snake.move();
					checkEat();
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	public void checkEat() {
		Head head = (Head) this.snake.getSnake().get(0);
		if((head.getXLocation() == food.getXLocation()) && (head.getYLocation() == food.getYLocation()))  {
			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore()+1);
			snake.getSnake().get((snake.getLength())).setVisible(true);
			snake.updateLength();
			snake.setLength(snake.getLength());
			moveToRandomLocation(food);
			System.out.println(food.getXLocation());
			System.out.println(food.getYLocation());
		}
	}
	
	public void moveToRandomLocation(Object o) {
		boolean isChanged = true;
		int currentX = ((Locatable) o).getXLocation();
		int currentY = ((Locatable) o).getYLocation();
		((Moveable)o).move();
		int newX = ((Locatable) o).getXLocation();
		int newY = ((Locatable) o).getYLocation();
		if((newX == currentX) && (newY == currentY)) {
			isChanged = false;
		}
		for (int i = 0;i<snake.getLength();i++) {
			if((newX == snake.getSnake().get(i).getXLocation()) && (newY == snake.getSnake().get(i).getYLocation())) {
				isChanged = false;
			}
		}
		while (!isChanged) {
			isChanged = true;
			((Moveable)o).move();
			newX = ((Locatable) o).getXLocation();
			newY = ((Locatable) o).getYLocation();
			if((newX == currentX) && (newY == currentY)) {
				isChanged = false;
			}
			for (int i = 0;i<snake.getLength();i++) {
				if((newX == snake.getSnake().get(i).getXLocation()) && (newY == snake.getSnake().get(i).getYLocation())) {
					isChanged = false;
				}
			}
		}
		if(o instanceof Node) {
			((Node) o).setTranslateX(newX);
			((Node) o).setTranslateY(newY);
		}
	}
}
