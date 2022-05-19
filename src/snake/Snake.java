package snake;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.Stamina;
import interfaces.Moveable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import logic.GameLogic;

public class Snake extends Pane  {
	private ArrayList<Body> snake;
	private int length;
	private boolean isCrash;
	private boolean canChangeDirection;//main purpose is for prevent user from press many direction key in a short time
	private int speed;
	private Stamina stamina;
	
	public Snake(int x, int y) {
		snake = new ArrayList<Body>();
		canChangeDirection = true;
		stamina = new Stamina(100);
		snake.add(new Head(x, y));
		snake.add(new Tail(x, y - 30));
		this.getChildren().add(snake.get(0));
		this.getChildren().add(snake.get(1));
		for (int i = 2; i < 40; i++) {
			snake.add(new Tail(x, y - 30 * i));
			snake.get(i).setVisible(false);
			this.getChildren().add(snake.get(i));
		}
		initialize();// initialize visibility, location, direction + update length
	}

	public ArrayList<Body> getSnake() {
		return snake;
	}

	public void setSnake(ArrayList<Body> snake) {
		this.snake = snake;
	}

	public int getLength() {
		return length;
	}

	public Head getHead() {
		return ((Head) this.snake.get(0));
	}

	public void setHead(Head head) {
		this.snake.set(0,head);
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void updateLength() {
		int l = 0;
		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).isVisible()) {
				l++;
			}
		}
		this.length = l;
	}

	public void changeLocation() {
		int x = getHead().getXLocation();
		int y = getHead().getYLocation();
		int d = getHead().getDirection();
		int headDirection = getHead().getDirection();
//		System.out.println("Case :" + this.direction);
		switch (headDirection) {
		case (0):
			getHead().setLocation(x - 30, y);
			break;
		case (1):
			getHead().setLocation(x, y - 30);
			break;
		case (2):
			getHead().setLocation(x + 30, y);
			break;
		case (3):
			getHead().setLocation(x, y + 30);
			break;
		}
		for (int i = 1; i < 40; i++) {
			

			
			Tail tail = (Tail) snake.get(i);
			int tempX = tail.getXLocation();
			int tempY = tail.getYLocation();
			int tempD = tail.getDirection();
			
			if(d != tempD) {
				tail.setRotate(90);
			}
			
			tail.setLocation(x, y);
			tail.setDirection(d);
			
			x = tempX;
			y = tempY;
			d = tempD;
		}
	}

	public void move() {
		for (int i = 0; i < 40; i++) {
			snake.get(i).setTranslateX(snake.get(i).getXLocation());
			snake.get(i).setTranslateY(snake.get(i).getYLocation());
		}
	}


	public boolean isCrash() {
		return isCrash;
	}

	public void setCrash(boolean isCrash) {
		this.isCrash = isCrash;
	}
	
	public boolean isCanChangeDirection() {
		return canChangeDirection;
	}

	public void setCanChangeDirection(boolean canChangeDirection) {
		this.canChangeDirection = canChangeDirection;
	}
	
	public Stamina getStamina() {
		return stamina;
	}

	public void setStamina(Stamina stamina) {
		this.stamina = stamina;
	}

	public void initialize() { // initialize visibility, location, direction
		for (int i = 0; i < 2; i++) {
			snake.get(i).setVisible(true);
		}
		for (int i = 2; i < 40; i++) {
			snake.get(i).setVisible(false);
		}
		((Head) snake.get(0)).initialize();
		for (int i = 1; i < 40; i++) {
			int x = snake.get(i - 1).getXLocation();
			int y = snake.get(i - 1).getYLocation();
			int dir = snake.get(i - 1).getDirection();
			snake.get(i).setLocation(x, y - 30);
			snake.get(i).setDirection(dir);
			snake.get(i).setTranslateX(snake.get(i).getXLocation());
			snake.get(i).setTranslateY(snake.get(i).getYLocation());
		}
		setCrash(false);
		stamina.setSp(100);
		updateLength();
	}

	
//	public int getXLocation() {
//		// TODO Auto-generated method stub
//		return getHead().getXLocation();
//	}
//
//	
//	public int getYLocation() {
//		// TODO Auto-generated method stub
//		return getHead().getYLocation();
//	}
//
//	
//	public Coordinate getLocation() {
//		// TODO Auto-generated method stub
//		return getHead().getLocation();
//	}
//
//	
//	public void setLocation(int x, int y) {
//		// TODO Auto-generated method stub
//		getHead().setLocation(x, y);
//	}
//
//	
//	public void randomLocation() {
//		// TODO Auto-generated method stub
//		Random rand = new Random();
//		getHead().setLocation(rand.nextInt(19)*30, rand.nextInt(19)*30);
//	}

}
